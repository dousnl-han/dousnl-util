package com.dousnl.vo.sparrow.menu.controller;

/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2024/11/7
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2024/11/7       hll    新增              1001
 ********************************************************************/

import com.dousnl.vo.sparrow.menu.database.DatabaseMetaDataUtil;
import com.dousnl.vo.sparrow.menu.dto.SparrowBackendConfigDTO;
import com.dousnl.vo.sparrow.menu.mapper.SparrowBackendConfigMapper;
import com.dousnl.vo.sparrow.menu.util.ConfigFileReader;
import com.dousnl.vo.sparrow.menu.vo.ColumnVO;
import com.dousnl.vo.sparrow.menu.vo.MenuConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/testJson")
public class TestJsonController {

    @Autowired
    private SparrowBackendConfigMapper sparrowBackendConfigMapper;
    
    
    @RequestMapping("/v1")
    public String v1(@RequestBody Map<String, Object> data) {
        List<SparrowBackendConfigDTO> addList = sparrowBackendConfigMapper.listByTemplateAlias("add");
        List<SparrowBackendConfigDTO> updateList = sparrowBackendConfigMapper.listByTemplateAlias("update");
        List<SparrowBackendConfigDTO> deleteList = sparrowBackendConfigMapper.listByTemplateAlias("delete");
        List<SparrowBackendConfigDTO> queryList = sparrowBackendConfigMapper.listByTemplateAlias("list");
        String filePath = (String) data.get("file");
        ConfigFileReader reader = new ConfigFileReader();
        MenuConfig config = reader.readConfigFile(filePath);

        Test test = new Test();
        test.setAddList(addList);
        test.setDeleteList(deleteList);
        test.setQueryList(queryList);
        test.setUpdateList(updateList);

        List<ColumnVO> coulumns = DatabaseMetaDataUtil.getCoulumns(config.getTable());
        config.setCoulumns(coulumns);



        return test.test(config);
    }


}
