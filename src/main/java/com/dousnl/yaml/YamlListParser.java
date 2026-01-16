package com.dousnl.yaml;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2024/11/20
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2024/11/20       hll    新增              1001
 ********************************************************************/
public class YamlListParser {

    public static void main(String[] args) {
        Yaml yaml = new Yaml();
        System.out.println("测试v2提交");
        try (InputStream inputStream = new FileInputStream("E:\\my-workspace\\dousnl-util\\src\\main\\java\\com\\dousnl\\yaml\\格式.yaml")) {
            // 解析为 Map
            Map<String, Object> config = yaml.load(inputStream);

            // 获取 "servers" 键的值，解析为 List
            List<Map<String, Object>> servers = (List<Map<String, Object>>) config.get("servers");

            // 遍历并打印每个服务器的详细信息
            for (int i = 0; i < servers.size(); i++) {
                Map<String, Object> server = servers.get(i);
                System.out.println("Server " + (i + 1) + ":");
                System.out.println("  Name: " + server.get("name"));
                System.out.println("  Host: " + server.get("host"));
                System.out.println("  Port: " + server.get("port"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
