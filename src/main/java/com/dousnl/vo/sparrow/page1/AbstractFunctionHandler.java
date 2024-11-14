package com.dousnl.vo.sparrow.page1;

/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2024/10/31
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2024/10/31       hll    新增              1001
 ********************************************************************/
public class AbstractFunctionHandler {

    private static BackendVO backendVO = new BackendVO();

    public BackendVO getBackendVO(String templateAlias, String endpointName, String path) {

        backendVO.setTemplateAlias(templateAlias);

        BackendEndpointVO endpointVO = new BackendEndpointVO();
        endpointVO.setName(endpointName);
        endpointVO.setPath(path);
        endpointVO.setType(1);
        endpointVO.setMethod("POST");
        endpointVO.setAppId("10000324");
        backendVO.setEndpoint(endpointVO);
        return backendVO;
    }

}
