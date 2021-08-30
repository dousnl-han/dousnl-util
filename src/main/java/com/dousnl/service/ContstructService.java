package com.dousnl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/4/18 11:29
 */
@DependsOn("dusyService")
@Service
public class ContstructService extends CommonService{

    @Autowired
    private DusyService dusyService;

    public ContstructService() {
        System.err.println(">>>>>>>>>>>ContstructService>>构造方法>>"+this.toString());
    }

    @PostConstruct
    public void DP(){
        System.err.println(">>>>>>>>>>>ContstructService>>>>"+this.toString());
    }
    @PreDestroy
    public void afterDP(){
        System.out.println(">>>>>>>>>>>ContstructService.busyService>>>"+dusyService.toString());
    }

    public DusyService getBusyService() {
        dusyService.DP();
        return dusyService;
    }
}
