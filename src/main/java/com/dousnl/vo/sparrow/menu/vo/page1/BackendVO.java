package com.dousnl.vo.sparrow.menu.vo.page1;

import com.dousnl.vo.sparrow.menu.vo.BackendEndpointVO;
import com.dousnl.vo.sparrow.menu.vo.BackendVariablesVO;
import lombok.Data;

import java.util.List;

/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2024/10/30
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2024/10/30       hll    新增              1001
 ********************************************************************/
@Data
public class BackendVO {

    private String templateAlias;

    private BackendEndpointVO endpoint;

    private List<BackendVariablesVO> variables;
}
