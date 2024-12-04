package com.dousnl.vo.sparrow.menu.controller;

import com.alibaba.fastjson.JSON;
import com.dousnl.utils.date.DateUtil;
import com.dousnl.vo.sparrow.menu.handler.VariableHandler;
import com.dousnl.vo.sparrow.menu.util.SpringContextUtils;
import com.dousnl.vo.sparrow.menu.vo.DeleteConfig;
import com.dousnl.vo.sparrow.menu.vo.InsertConfig;
import com.dousnl.vo.sparrow.menu.vo.MenuConfig;
import com.dousnl.vo.sparrow.menu.dto.SparrowBackendConfigDTO;
import com.dousnl.vo.sparrow.menu.util.ChineseToPinyinUtil;
import com.dousnl.vo.sparrow.menu.vo.QueryConfig;
import com.dousnl.vo.sparrow.menu.vo.UpdateConfig;
import com.dousnl.vo.sparrow.menu.vo.BackendEndpointVO;
import com.dousnl.vo.sparrow.menu.vo.BackendVO;
import com.dousnl.vo.sparrow.menu.vo.BackendVariablesVO;
import com.dousnl.vo.sparrow.menu.vo.SparrowVO;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;

/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2024/11/6
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2024/11/6       hll    新增              1001
 ********************************************************************/
public class Test {

    private static final String TEMPLATE_CODE_PREFIX = "/sparrowAdmin";
    public static final HashMap<String, String> templateCodeMap = new HashMap<>();

    static {
        templateCodeMap.put("insertTargetRule", "{\"insertType\":1,\"tableRule\":{\"datasourceName\":\"${datasourceName}\",\"tableName\":\"${tableName}\"}}");
        templateCodeMap.put("insertParam", "{\"tableInsertDTO\":\"${insertParam}\"}");
        templateCodeMap.put("onlyOneCheck",
                "{\"datasourceName\":\"${datasourceName}\",\"tableName\":\"${tableName}\",\"fields\":\"${fields}\",\"errorCode\":\"${errorCode}\",\"errorMsg\":\"${errorMsg}\"}");
        templateCodeMap.put("updateTargetRule", "{\"updateType\":1,\"tableRule\":{\"datasourceName\":\"${datasourceName}\",\"tableName\":\"${tableName}\"}}");
        templateCodeMap.put("updateParam", "{\"tableUpdateDTO\":\"${updateParam}\"}");
        templateCodeMap.put("updateCondition", "{\"tableConditionDTO\":\"${updateCondition}\"}");

        templateCodeMap.put("rulesDB", "[{\"field\":[{\"sourceField\":\"${sourceField}\",\"tableField\":\"${tableField}\",\"compare\":\"${compare}\",\"connect\":\"AND\"}]," +
                "\"datasourceName\":\"${datasourceName}\",\"tableName\":\"${tableName}\",\"transField\":\"${transField}\"}]");
        templateCodeMap.put("queryTargetRule", "{\"queryType\":1,\"tableRule\":\"${tableRule}\",\"outputRule\":{\"outputType\":3}}");
        templateCodeMap.put("queryParam",
                "{\"tableQueryDTO\":\"${tableQueryDTO}\",\"pageDTO\":[{\"tableField\":\"pageNo\",\"queryField\":\"pageNo\"},{\"tableField\":\"pageSize\",\"queryField\":\"pageSize\"}]}");
    }

    public List<SparrowBackendConfigDTO> addList = Lists.newArrayList();
    public List<SparrowBackendConfigDTO> updateList = Lists.newArrayList();
    public List<SparrowBackendConfigDTO> deleteList = Lists.newArrayList();
    public List<SparrowBackendConfigDTO> queryList = Lists.newArrayList();

    public List<SparrowBackendConfigDTO> getAddList() {
        return addList;
    }

    public void setAddList(List<SparrowBackendConfigDTO> addList) {
        this.addList = addList;
    }

    public List<SparrowBackendConfigDTO> getUpdateList() {
        return updateList;
    }

    public void setUpdateList(List<SparrowBackendConfigDTO> updateList) {
        this.updateList = updateList;
    }

    public List<SparrowBackendConfigDTO> getDeleteList() {
        return deleteList;
    }

    public void setDeleteList(List<SparrowBackendConfigDTO> deleteList) {
        this.deleteList = deleteList;
    }

    public List<SparrowBackendConfigDTO> getQueryList() {
        return queryList;
    }

    public void setQueryList(List<SparrowBackendConfigDTO> queryList) {
        this.queryList = queryList;
    }

    public String test(MenuConfig menuConfig) {

        SparrowVO sparrowVO = new SparrowVO();

        //1.功能描述获取模板code
        String functionDesc = menuConfig.getFunctionDesc();
        String templateCode = getTemplateCode(functionDesc);
        sparrowVO.setTemplateCode(templateCode);
        List<BackendVO> backendList = Lists.newArrayList();
        //2.定义后端VO
        sparrowVO.setBackend(backendList);

        //3.增删改查
        insertBackendList(menuConfig, backendList);
        updateBackendList(menuConfig, backendList);
        deleteBackendList(menuConfig, backendList);
        queryBackendList(menuConfig, backendList);
        System.out.println(JSON.toJSONString(sparrowVO));

        return JSON.toJSONString(sparrowVO);
    }

    private void queryBackendList(MenuConfig menuConfig, List<BackendVO> backendList) {
        List<QueryConfig> insertConfigs = menuConfig.getQueryConfigs();
        for (QueryConfig insertConfig : insertConfigs) {
            //2.构建，templateAlias，endpoint
            BackendVO backendVO = new BackendVO();
            backendVO.setTemplateAlias(insertConfig.getTemplateAlias() + ChineseToPinyinUtil.toPinyin(insertConfig.getName()));
            BackendEndpointVO endpointVO = new BackendEndpointVO();
            endpointVO.setType(1);
            endpointVO.setMethod("POST");
            endpointVO.setPath(TEMPLATE_CODE_PREFIX + "/" + backendVO.getTemplateAlias() + "/" + DateUtil.dateToString(DateUtil.getCurrentDate(),
                    "yyyyMMddHH"));
            endpointVO.setName(insertConfig.getName() + DateUtil.dateToString(DateUtil.getCurrentDate(),
                    "yyyyMMddHH"));
            endpointVO.setAppId("10000324");
            backendVO.setEndpoint(endpointVO);
            List<BackendVariablesVO> variablesVOS = Lists.newArrayList();
            backendVO.setVariables(variablesVOS);

            for (SparrowBackendConfigDTO sparrowBackendConfigDTO : queryList) {
                String code = sparrowBackendConfigDTO.getCode();
                Object bean = SpringContextUtils.getBean(code);
                if (bean instanceof VariableHandler) {
                    VariableHandler variableHandler = (VariableHandler) bean;
                    BackendVariablesVO variablesVO = variableHandler.convertVariables(sparrowBackendConfigDTO, menuConfig, insertConfig);
                    if (variablesVO != null) {
                        variablesVOS.add(variablesVO);
                    }
                }
            }
            backendList.add(backendVO);
        }
    }

    private void deleteBackendList(MenuConfig menuConfig, List<BackendVO> backendList) {
        List<DeleteConfig> insertConfigs = menuConfig.getDeleteConfigs();
        for (DeleteConfig insertConfig : insertConfigs) {
            //2.构建，templateAlias，endpoint
            BackendVO backendVO = new BackendVO();
            backendVO.setTemplateAlias(insertConfig.getTemplateAlias() + ChineseToPinyinUtil.toPinyin(insertConfig.getName()));
            BackendEndpointVO endpointVO = new BackendEndpointVO();
            endpointVO.setType(1);
            endpointVO.setMethod("POST");
            endpointVO.setPath(TEMPLATE_CODE_PREFIX + "/" + backendVO.getTemplateAlias() + "/" + DateUtil.dateToString(DateUtil.getCurrentDate(),
                    "yyyyMMddHH"));
            endpointVO.setName(insertConfig.getName() + DateUtil.dateToString(DateUtil.getCurrentDate(),
                    "yyyyMMddHH"));
            endpointVO.setAppId("10000324");
            backendVO.setEndpoint(endpointVO);
            List<BackendVariablesVO> variablesVOS = Lists.newArrayList();
            backendVO.setVariables(variablesVOS);

            for (SparrowBackendConfigDTO sparrowBackendConfigDTO : deleteList) {
                String code = sparrowBackendConfigDTO.getCode();
                Object bean = SpringContextUtils.getBean(code);
                if (bean instanceof VariableHandler) {
                    VariableHandler variableHandler = (VariableHandler) bean;
                    BackendVariablesVO variablesVO = variableHandler.convertVariables(sparrowBackendConfigDTO, menuConfig, insertConfig);
                    if (variablesVO != null) {
                        variablesVOS.add(variablesVO);
                    }
                }
            }
            backendList.add(backendVO);
        }
    }

    private void updateBackendList(MenuConfig menuConfig, List<BackendVO> backendList) {
        List<UpdateConfig> insertConfigs = menuConfig.getUpdateConfigs();
        for (UpdateConfig insertConfig : insertConfigs) {
            //2.构建，templateAlias，endpoint
            BackendVO backendVO = new BackendVO();
            backendVO.setTemplateAlias(insertConfig.getTemplateAlias() + ChineseToPinyinUtil.toPinyin(insertConfig.getName()));
            BackendEndpointVO endpointVO = new BackendEndpointVO();
            endpointVO.setType(1);
            endpointVO.setMethod("POST");
            endpointVO.setPath(TEMPLATE_CODE_PREFIX + "/" + backendVO.getTemplateAlias()
                    + "/" + DateUtil.dateToString(DateUtil.getCurrentDate(),
                    "yyyyMMddHH"));
            endpointVO.setName(insertConfig.getName() + DateUtil.dateToString(DateUtil.getCurrentDate(),
                    "yyyyMMddHH"));
            endpointVO.setAppId("10000324");
            backendVO.setEndpoint(endpointVO);
            List<BackendVariablesVO> variablesVOS = Lists.newArrayList();
            backendVO.setVariables(variablesVOS);

            for (SparrowBackendConfigDTO sparrowBackendConfigDTO : updateList) {
                String code = sparrowBackendConfigDTO.getCode();
                Object bean = SpringContextUtils.getBean(code);
                if (bean instanceof VariableHandler) {
                    VariableHandler variableHandler = (VariableHandler) bean;
                    BackendVariablesVO variablesVO = variableHandler.convertVariables(sparrowBackendConfigDTO, menuConfig, insertConfig);
                    if (variablesVO != null) {
                        variablesVOS.add(variablesVO);
                    }
                }
            }
            backendList.add(backendVO);
        }
    }

    private void insertBackendList(MenuConfig menuConfig, List<BackendVO> backendList) {
        List<InsertConfig> insertConfigs = menuConfig.getInsertConfigs();
        for (InsertConfig insertConfig : insertConfigs) {
            //2.构建，templateAlias，endpoint
            BackendVO backendVO = new BackendVO();
            backendVO.setTemplateAlias(insertConfig.getTemplateAlias() + ChineseToPinyinUtil.toPinyin(insertConfig.getName()));
            BackendEndpointVO endpointVO = new BackendEndpointVO();
            endpointVO.setType(1);
            endpointVO.setMethod("POST");
            endpointVO.setPath(TEMPLATE_CODE_PREFIX + "/" + backendVO.getTemplateAlias()
                    + "/" + DateUtil.dateToString(DateUtil.getCurrentDate(), "yyyyMMddHH"));
            endpointVO.setName(insertConfig.getName() + DateUtil.dateToString(DateUtil.getCurrentDate(),
                    "yyyyMMddHH"));
            endpointVO.setAppId("10000324");
            backendVO.setEndpoint(endpointVO);
            List<BackendVariablesVO> variablesVOS = Lists.newArrayList();
            backendVO.setVariables(variablesVOS);

            for (SparrowBackendConfigDTO sparrowBackendConfigDTO : addList) {
                String code = sparrowBackendConfigDTO.getCode();
                Object bean = SpringContextUtils.getBean(code);
                if (bean instanceof VariableHandler) {
                    VariableHandler variableHandler = (VariableHandler) bean;
                    BackendVariablesVO variablesVO = variableHandler.convertVariables(sparrowBackendConfigDTO, menuConfig, insertConfig);
                    if (variablesVO != null) {
                        variablesVOS.add(variablesVO);
                    }
                }
            }
            backendList.add(backendVO);
        }
    }

    /**
     * todo
     * 根据功能描述获取模板code
     *
     * @param functionDesc
     * @return
     */
    private String getTemplateCode(String functionDesc) {
        return functionDesc;
    }


}
