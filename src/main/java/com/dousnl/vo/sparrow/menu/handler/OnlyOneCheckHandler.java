package com.dousnl.vo.sparrow.menu.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dousnl.utils.StringUtil;
import com.dousnl.vo.sparrow.menu.constant.SparrowBackendConstant;
import com.dousnl.vo.sparrow.menu.enums.TemplateAliasEnum;
import com.dousnl.vo.sparrow.menu.util.SnakeToCamelUtil;
import com.dousnl.vo.sparrow.menu.vo.BackConfig;
import com.dousnl.vo.sparrow.menu.vo.MenuConfig;
import com.dousnl.vo.sparrow.menu.dto.SparrowBackendConfigDTO;
import com.dousnl.vo.sparrow.page1.BackendVariablesVO;
import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2024/11/7
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2024/11/7       hll    新增              1001
 ********************************************************************/
@Component("onlyOneCheck")
public class OnlyOneCheckHandler implements VariableHandler {
    @Override
    public BackendVariablesVO convertVariables(SparrowBackendConfigDTO sparrowBackendConfigDTO, MenuConfig menuConfig, BackConfig backConfig) {
        BackendVariablesVO backendVariablesVO = createBackendVariablesVO(sparrowBackendConfigDTO);
        List<String> onlyOneCheck = backConfig.getOnlyOneCheck();
        if (CollectionUtils.isEmpty(onlyOneCheck)) {
            return null;
        }
        Map<String, String> columnCommentMap = menuConfig.getColumnCommentMap();

        List<OnlyOneCheckDTO> oneCheckDTOS = Lists.newArrayList();

        for (String onlyOneCheckStr : onlyOneCheck) {
            if (StringUtil.isBlank(onlyOneCheckStr)) {
                continue;
            }
            String[] onlyOneCheckFields = onlyOneCheckStr.split(SparrowBackendConstant.COLON_SEPARATOR);
            //效验的字段
            if (onlyOneCheckFields.length != 3) {
                throw new RuntimeException(onlyOneCheckStr + "配置错误");
            }
            String[] fields = onlyOneCheckFields[0].split(SparrowBackendConstant.COMMA_SEPARATOR);
            String errorMsg = onlyOneCheckFields[1];
            String errorCode = onlyOneCheckFields[2];

            OnlyOneCheckDTO onlyOneCheckDTO = new OnlyOneCheckDTO();
            onlyOneCheckDTO.setDatasourceName(menuConfig.getDatabase());
            onlyOneCheckDTO.setTableName(menuConfig.getTable());
            onlyOneCheckDTO.setErrorCode(errorCode);
            onlyOneCheckDTO.setErrorMsg(errorMsg);

            List<JSONObject> arrayList = Lists.newArrayList();
            for (String field : fields) {
                String[] singleField = field.split(SparrowBackendConstant.SLASH_SEPARATOR);
                if (singleField.length != 2 && singleField.length != 3) {
                    throw new RuntimeException(field + "配置错误");
                }
                JSONObject jsonObject = new JSONObject();

                String realTableField = columnCommentMap.get(singleField[0]);
                if (StringUtil.isBlank(realTableField)) {
                    throw new IllegalArgumentException("字段:" + singleField[0] + "没有对应的数据库字段");
                }
                String rightTableField = realTableField;
                String linker = singleField[1];
                if (singleField.length == 3) {
                    String rightTableFieldColumn = columnCommentMap.get(singleField[1]);
                    if (StringUtil.isBlank(rightTableFieldColumn)) {
                        rightTableField = singleField[1];
                    } else {
                        rightTableField = rightTableFieldColumn;
                    }
                    linker = singleField[2];
                }


                jsonObject.put("tableField", realTableField);
                if (Objects.equals(sparrowBackendConfigDTO.getTemplateAlias(), TemplateAliasEnum.add.name())) {
                    jsonObject.put("insertField", SnakeToCamelUtil.toCamelCase(rightTableField));
                } else if (Objects.equals(sparrowBackendConfigDTO.getTemplateAlias(), TemplateAliasEnum.update.name())
                        || Objects.equals(sparrowBackendConfigDTO.getTemplateAlias(), TemplateAliasEnum.delete.name())) {
                    jsonObject.put("updateField", SnakeToCamelUtil.toCamelCase(rightTableField));
                }
                jsonObject.put("linker", linker);
                arrayList.add(jsonObject);
            }
            onlyOneCheckDTO.setFields(arrayList);
            oneCheckDTOS.add(onlyOneCheckDTO);
        }
        backendVariablesVO.setValue(JSON.toJSONString(oneCheckDTOS));
        return backendVariablesVO;
    }


    @Data
    public static class OnlyOneCheckDTO {
        private String datasourceName;
        private String tableName;
        private List<JSONObject> fields;
        private String errorCode;
        private String errorMsg;
    }
}
