package com.dousnl.vo.sparrow.menu.handler;

import com.alibaba.fastjson.JSON;
import com.dousnl.utils.StringUtil;
import com.dousnl.vo.sparrow.menu.constant.SparrowBackendConstant;
import com.dousnl.vo.sparrow.menu.database.DatabaseMetaDataUtil;
import com.dousnl.vo.sparrow.menu.dto.SparrowBackendConfigDTO;
import com.dousnl.vo.sparrow.menu.enums.MultiTableQueryEnum;
import com.dousnl.vo.sparrow.menu.enums.MultiTableQueryFieldEnum;
import com.dousnl.vo.sparrow.menu.util.SnakeToCamelUtil;
import com.dousnl.vo.sparrow.menu.vo.BackConfig;
import com.dousnl.vo.sparrow.menu.vo.BackendVariablesVO;
import com.dousnl.vo.sparrow.menu.vo.ColumnVO;
import com.dousnl.vo.sparrow.menu.vo.MenuConfig;
import com.dousnl.vo.sparrow.menu.vo.QueryConfig;
import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2024/11/7
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2024/11/7       hll    新增              1001
 ********************************************************************/
@Component("queryParam")
public class QueryParamHandler implements VariableHandler {


    @Override
    public BackendVariablesVO convertVariables(SparrowBackendConfigDTO sparrowBackendConfigDTO, MenuConfig menuConfig, BackConfig backConfig) {
        BackendVariablesVO backendVariablesVO = createBackendVariablesVO(sparrowBackendConfigDTO);

        String config = sparrowBackendConfigDTO.getConfig();
        QueryConfig queryConfig = (QueryConfig) backConfig;

        String conditions = queryConfig.getConditions();
        if (StringUtil.isBlank(conditions)) {
            return backendVariablesVO;
        }
        //多表关联查询
        if (!StringUtil.isBlank(queryConfig.getMultiTables())) {
            multitablerQuery(config, queryConfig, backendVariablesVO, conditions);
            return backendVariablesVO;
        }

        Map<String, String> columnCommentMap = menuConfig.getColumnCommentMap();
        if (queryConfig.getTable() != null && !Objects.equals(queryConfig.getTable(), menuConfig.getTable())) {
            List<ColumnVO> coulumns = DatabaseMetaDataUtil.getCoulumns(queryConfig.getTable());
            columnCommentMap = coulumns.stream().collect(Collectors.toMap(ColumnVO::getComment, ColumnVO::getColumnName, (v1, v2) -> v1));
        }

        String[] conditionsArray = conditions.split(SparrowBackendConstant.COMMA_SEPARATOR);

        List<TableQueryDTO> tableQueryDTOS = Lists.newArrayList();
        for (String conditionName : conditionsArray) {
            String[] queryFields = conditionName.split(SparrowBackendConstant.SLASH_SEPARATOR);
            if (queryFields.length != 2 && queryFields.length != 3) {
                throw new RuntimeException(queryFields + "配置错误");
            }
            String tableField = queryFields[0];

            String realTableField = columnCommentMap.get(tableField);
            if (StringUtil.isBlank(realTableField)) {
                throw new IllegalArgumentException("字段:" + tableField + "没有对应的数据库字段");
            }
            String rightTableField = realTableField;
            String linker = queryFields[1];
            if (queryFields.length == 3) {
                String rightTableFieldColumn = columnCommentMap.get(queryFields[1]);
                if (StringUtil.isBlank(rightTableFieldColumn)) {
                    rightTableField = queryFields[1];
                } else {
                    rightTableField = rightTableFieldColumn;
                }
                linker = queryFields[2];
            }

            TableQueryDTO tableQueryDTO = new TableQueryDTO();
            tableQueryDTO.setTableField(realTableField);
            tableQueryDTO.setQueryField(SnakeToCamelUtil.toCamelCase(rightTableField));
            tableQueryDTO.setLinker(linker);
            tableQueryDTOS.add(tableQueryDTO);
        }
        config = config.replace("${tableQueryDTO}", JSON.toJSONString(tableQueryDTOS));
        backendVariablesVO.setValue(config);

        return backendVariablesVO;
    }

    private BackendVariablesVO multitablerQuery(String config, QueryConfig queryConfig, BackendVariablesVO backendVariablesVO, String conditions) {
        Map<String, String> columnCommentMap = new HashMap<>();
        String[] tables = queryConfig.getMultiTables().split(SparrowBackendConstant.COMMA_SEPARATOR);
        for (String table : tables) {
            List<ColumnVO> coulumns = DatabaseMetaDataUtil.getCoulumns(table);
            columnCommentMap.putAll(coulumns.stream().collect(Collectors.toMap(ColumnVO::getComment, ColumnVO::getColumnName, (v1, v2) -> v1)));
        }
        for (MultiTableQueryEnum value : MultiTableQueryEnum.values()) {
            columnCommentMap.put(value.getDesc(), value.getCode());
        }
        for (MultiTableQueryFieldEnum value : MultiTableQueryFieldEnum.values()) {
            columnCommentMap.put(value.getDesc(), value.getCode());
        }

        String[] conditionsArray = conditions.split(SparrowBackendConstant.COMMA_SEPARATOR);

        List<TableQueryDTO> tableQueryDTOS = Lists.newArrayList();
        for (String conditionName : conditionsArray) {
            String[] queryFields = conditionName.split(SparrowBackendConstant.SLASH_SEPARATOR);
            if (queryFields.length != 2) {
                throw new RuntimeException(queryFields + "配置错误");
            }
            String tableField = queryFields[0];

            String realTableField = replaceColumnsWithComments(tableField, columnCommentMap);

            String linker = queryFields[1];
            TableQueryDTO tableQueryDTO = new TableQueryDTO();
            tableQueryDTO.setTableField(realTableField);
            tableQueryDTO.setQueryField(SnakeToCamelUtil.toCamelCase(realTableField));
            tableQueryDTO.setLinker(linker);
            tableQueryDTOS.add(tableQueryDTO);
        }
        config = config.replace("${tableQueryDTO}", JSON.toJSONString(tableQueryDTOS));
        backendVariablesVO.setValue(config);

        return backendVariablesVO;
    }

    public static String replaceColumnsWithComments(String str, Map<String, String> columnCommentMap) {
        String result = str;
        for (Map.Entry<String, String> entry : columnCommentMap.entrySet()) {
            String column = entry.getKey();
            String comment = entry.getValue();
            result = result.replaceAll("\\b" + column + "\\b", comment);
        }
        return result;
    }

    @Data
    public static class TableQueryDTO {
        private String tableField;
        private String queryField;
        private String linker;
    }

}
