package com.dousnl.controller;

import com.dousnl.domain.es.EsIndexAndTypeParam;
import com.dousnl.domain.es.User;
import com.google.common.collect.Maps;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2020/12/25
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2020/12/25       hanliangliang     新增              1001
 ********************************************************************/
@RestController
@RequestMapping("/es")
public class EsRestHighClientController {

    @Autowired
    private RestHighLevelClient client;

    @PostMapping("/index/create")
    public Integer createIndex(@RequestBody EsIndexAndTypeParam param){
        IndexRequest index=new IndexRequest(param.getIndex(),param.getType());
        Map map= Maps.newHashMap();
        map.put("name","zhangsan");
        map.put("age",18);
        map.put("address","上海市长宁区遵义路100号南丰城A栋23楼");
        index.source(map,XContentType.JSON);
        try {
            IndexResponse response = client.index(index, RequestOptions.DEFAULT);
            RestStatus status = response.status();
            System.out.println(status.getStatus());
            return status.getStatus();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @PutMapping("/add")
    public Integer addUser(@RequestBody User param){
        IndexRequest index=new IndexRequest("userindex","user");
        Map map= Maps.newHashMap();
        map.put("name","lisi");
        map.put("age",20);
        map.put("address","上海市青浦区诸光路100号");
        index.source(map,XContentType.JSON);
        try {
            IndexResponse response = client.index(index, RequestOptions.DEFAULT);
            RestStatus status = response.status();
            return status.getStatus();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @GetMapping("/getOne")
    public Integer getOne(@RequestParam Integer id){
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder builder=new SearchSourceBuilder();

        //QueryBuilder queryBuilder= QueryBuilders.matchPhraseQuery();
        builder.query();
        searchRequest.source(builder);
        try {
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            RestStatus status = response.status();
            return status.getStatus();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
