package com.dousnl.service;

import org.springframework.beans.factory.annotation.Autowired;
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
@Service
public class ContstructService extends CommonService{

    @Autowired
    private BusyService busyService;

    @PostConstruct
    public void DP(){
        System.out.println(">>>>>>>>>>>ContstructService>>>>"+this.toString());
    }
    @PreDestroy
    public void afterDP(){
        System.out.println(">>>>>>>>>>>ContstructService.busyService>>>"+busyService.toString());
    }

    public BusyService getBusyService() {
        busyService.DP();
        return busyService;
    }
}
