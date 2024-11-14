package com.dousnl.vo.sparrow.menu.handler;

import com.alibaba.fastjson.JSON;
import com.dousnl.utils.StringUtil;
import com.dousnl.vo.sparrow.menu.constant.SparrowBackendConstant;
import com.dousnl.vo.sparrow.menu.database.DatabaseMetaDataUtil;
import com.dousnl.vo.sparrow.menu.vo.BackConfig;
import com.dousnl.vo.sparrow.menu.vo.ColumnVO;
import com.dousnl.vo.sparrow.menu.vo.InsertConfig;
import com.dousnl.vo.sparrow.menu.vo.MenuConfig;
import com.dousnl.vo.sparrow.menu.dto.SparrowBackendConfigDTO;
import com.dousnl.vo.sparrow.page1.BackendVariablesVO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Arrays;
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
@Component("insertTargetRule")
public class InsertTargetRuleHandler implements VariableHandler {
    @Override
    public BackendVariablesVO convertVariables(SparrowBackendConfigDTO sparrowBackendConfigDTO, MenuConfig menuConfig, BackConfig backConfig) {
        BackendVariablesVO backendVariablesVO = createBackendVariablesVO(sparrowBackendConfigDTO);

        String config = sparrowBackendConfigDTO.getConfig();

        InsertTargetTableRuleDTO targetTableRuleDTO = new InsertTargetTableRuleDTO();
        targetTableRuleDTO.setDatasourceName(menuConfig.getDatabase());
        targetTableRuleDTO.setTableName(menuConfig.getTable());
        InsertConfig insertConfig = (InsertConfig) backConfig;

        String tableName = insertConfig.getTableName();
        Map<String, String> columnCommentMap = menuConfig.getColumnCommentMap();
        if (tableName != null && !Objects.equals(tableName, menuConfig.getTable())) {
            targetTableRuleDTO.setTableName(tableName);
            List<ColumnVO> coulumns = DatabaseMetaDataUtil.getCoulumns(tableName);
            columnCommentMap = coulumns.stream().collect(Collectors.toMap(ColumnVO::getComment, ColumnVO::getColumnName, (v1, v2) -> v1));
        }

        if (!StringUtil.isBlank(insertConfig.getBatchInsertFields())) {
            targetTableRuleDTO.setBatchInsertType(2);
            String[] batchInsertFields = insertConfig.getBatchInsertFields().split(SparrowBackendConstant.COMMA_SEPARATOR);

            for (int i = 0; i < batchInsertFields.length; i++) {
                String realTableField = columnCommentMap.get(batchInsertFields[i]);
                batchInsertFields[i] = realTableField;
            }

            targetTableRuleDTO.setBatchInsertDTOParam(Arrays.asList(batchInsertFields));
        }
        config = config.replace("${tableRule}", JSON.toJSONString(targetTableRuleDTO));
        backendVariablesVO.setValue(config);
        return backendVariablesVO;
    }

    @Data
    public static class InsertTargetTableRuleDTO {
        private String datasourceName;
        private String tableName;
        private Integer batchInsertType;
        private List<String> batchInsertDTOParam;
    }
}
