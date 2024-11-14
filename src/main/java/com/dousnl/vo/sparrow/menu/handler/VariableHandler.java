package com.dousnl.vo.sparrow.menu.handler;

import com.dousnl.vo.sparrow.menu.vo.BackConfig;
import com.dousnl.vo.sparrow.menu.vo.MenuConfig;
import com.dousnl.vo.sparrow.menu.dto.SparrowBackendConfigDTO;
import com.dousnl.vo.sparrow.page1.BackendVariablesVO;

/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2024/11/7
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2024/11/7       hll    新增              1001
 ********************************************************************/
public interface VariableHandler {

    BackendVariablesVO convertVariables(SparrowBackendConfigDTO sparrowBackendConfigDTO, MenuConfig menuConfig, BackConfig backConfig);

    default BackendVariablesVO createBackendVariablesVO(SparrowBackendConfigDTO sparrowBackendConfigDTO) {
        BackendVariablesVO backendVariablesVO = new BackendVariablesVO();
        backendVariablesVO.setName(sparrowBackendConfigDTO.getCode());
        backendVariablesVO.setDesc(sparrowBackendConfigDTO.getDesc());
        backendVariablesVO.setType(sparrowBackendConfigDTO.getType());
        return backendVariablesVO;
    }
}
