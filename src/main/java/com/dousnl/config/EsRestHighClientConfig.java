package com.dousnl.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2020/12/25
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2020/12/25       hanliangliang     新增              1001
 ********************************************************************/
@Configuration
public class EsRestHighClientConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestClientBuilder builder= RestClient.builder(new HttpHost("localhost",9200));
        return new RestHighLevelClient(builder);
    }
}
