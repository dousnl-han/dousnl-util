package com.dousnl.vo.sparrow.menu.vo.page1;

/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2024/11/6
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2024/11/6       hll    新增              1001
 ********************************************************************/

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;

import java.io.*;
import java.util.Properties;

public class ConfigUtil {
    public static Properties properties;

    public ConfigUtil(String filePath) {
        properties = new Properties();
        try {
            properties.load(FileUtil.getUtf8Reader(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public int getIntProperty(String key) {
        String value = properties.getProperty(key);
        return value != null ? Integer.parseInt(value) : 0;
    }

    public boolean getBooleanProperty(String key) {
        String value = properties.getProperty(key);
        return value != null ? Boolean.parseBoolean(value) : false;
    }

    public static void main(String[] args) {
        ConfigUtil config = new ConfigUtil("D:\\application.properties");

        YunFanPropertiesVO yunFanPropertiesVO = JSON.parseObject(JSON.toJSONString(properties), YunFanPropertiesVO.class);
        System.out.println(yunFanPropertiesVO.getDataSource());
    }
}

