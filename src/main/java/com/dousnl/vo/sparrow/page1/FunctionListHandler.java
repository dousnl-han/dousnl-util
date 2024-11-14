package com.dousnl.vo.sparrow.page1;

import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2024/10/31
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2024/10/31       hll    新增              1001
 ********************************************************************/
public class FunctionListHandler extends AbstractFunctionHandler {

    private static FunctionChildrenVO functionChildrenVO;

    public static String getTemplateAlias() {
        return "list";
    }

    public static String getPath() {
        return "/sparrowAdmin" + functionChildrenVO.getPath() + DateUtil.format(new Date(), "yyyyMMdd");
    }

    public static String getEndpointNameName() {
        return functionChildrenVO.getEndpointName();
    }

    public void buildData() {
        BackendVO backendVO = getBackendVO(getTemplateAlias(), getEndpointNameName(), getPath());
        List<BackendVariablesVO> arrayList = Lists.newArrayList();
        BackendVariablesVO queryTargetRule = new BackendVariablesVO();
        queryTargetRule.setName("queryTargetRule");
        queryTargetRule.setDesc("查询目标规则信息，支持直接查询表or调用rpc，queryType：1DB 2RPC，查询表时支持多表查询");
        queryTargetRule.setType(1);
        queryTargetRule.setValue("{\\\"queryType\\\":1,\\\"tableRule\\\":{\\\"tableDataSource\\\":\\\"sam\\\",\\\"tableField\\\":\\\"tid, role_name as roleName, role_type, source, user_num as userNum, channel_flag, remark, create_id, create_time, update_id, update_time, del_flag\\\",\\\"tableName\\\":\\\"t_cms_role\\\",\\\"tablePage\\\":\\\"pageDTO\\\",\\\"tableSort\\\":\\\"order by tid desc\\\",\\\"tableTotalCount\\\":2},\\\"outputRule\\\":{\\\"outputType\\\":3}}");
        arrayList.add(queryTargetRule);


        backendVO.setVariables(arrayList);
    }

}
