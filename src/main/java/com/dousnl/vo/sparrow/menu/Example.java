package com.dousnl.vo.sparrow.menu;

import com.dousnl.vo.sparrow.menu.util.ConfigFileReader;
import com.dousnl.vo.sparrow.menu.vo.DeleteConfig;
import com.dousnl.vo.sparrow.menu.vo.InsertConfig;
import com.dousnl.vo.sparrow.menu.vo.MenuConfig;
import com.dousnl.vo.sparrow.menu.vo.QueryConfig;
import com.dousnl.vo.sparrow.menu.vo.UpdateConfig;

/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2024/11/6
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2024/11/6       hll    新增              1001
 ********************************************************************/
public class Example {
    public static void main(String[] args) {
        ConfigFileReader reader = new ConfigFileReader();
        MenuConfig config = reader.readConfigFile("D:\\application.properties");

        // 验证读取结果
        System.out.println("基本信息:");
        System.out.println("功能描述: " + config.getFunctionDesc());
        System.out.println("数据库: " + config.getDatabase());
        System.out.println("表: " + config.getTable());

        // 打印新增配置
        System.out.println("\n");
        for (int i = 0; i < config.getInsertConfigs().size(); i++) {
            System.out.println("新增配置[" + i + "]:");
            InsertConfig insertConfig = config.getInsertConfigs().get(i);
            System.out.println("参数字段: " + insertConfig.getInsertFields());
            System.out.println("唯一性校验: " + insertConfig.getOnlyOneCheck());
        }

        // 打印更新配置
        System.out.println("\n");
        for (int i = 0; i < config.getUpdateConfigs().size(); i++) {
            System.out.println("更新配置[" + i + "]:");
            UpdateConfig updateConfig = config.getUpdateConfigs().get(i);
            System.out.println("参数字段: " + updateConfig.getUpdateFields());
            System.out.println("条件字段: " + updateConfig.getConditions());
            System.out.println("唯一性校验: " + updateConfig.getOnlyOneCheck());
        }

        // ... 打印其他配置
        System.out.println("\n");
        for (int i = 0; i < config.getDeleteConfigs().size(); i++) {
            System.out.println("删除配置[" + i + "]:");
            DeleteConfig updateConfig = config.getDeleteConfigs().get(i);
            System.out.println("参数字段: " + updateConfig.getUpdateFields());
            System.out.println("条件字段: " + updateConfig.getConditions());
            System.out.println("唯一性校验: " + updateConfig.getOnlyOneCheck());
            System.out.println("转换规则: " + updateConfig.getRuleDB());
        }

        // ... 打印其他配置
        System.out.println("\n");
        for (int i = 0; i < config.getDeleteConfigs().size(); i++) {
            System.out.println("查询配置[" + i + "]:");
            QueryConfig updateConfig = config.getQueryConfigs().get(i);
            System.out.println("查询字段: " + updateConfig.getQueryFields());
            System.out.println("查询表: " + updateConfig.getTable());
            System.out.println("是否分页: " + updateConfig.isPagination());
            System.out.println("排序字段: " + updateConfig.getSortField());
            System.out.println("查询入参: " + updateConfig.getConditions());
        }
    }
}
