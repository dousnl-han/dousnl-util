package com.dousnl.strategy;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 计算操作容器
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/6/30 14:47
 */
@Component
public class CalculateOpertionContext {

    private final Map<String,CalculateStrategy> strategyMap=new ConcurrentHashMap<>();

    @Autowired
    public void strategyInferfance(Map<String,CalculateStrategy> strategyMap){
        this.strategyMap.clear();
        strategyMap.forEach(this.strategyMap::put);
        System.out.println(this.strategyMap);
    }

    @Autowired
    public void strategyInferfance2(List<CalculateStrategy> strategyList){
        strategyList.forEach(System.out::println);
    }

    public CalculateStrategy selectStrategy(String mode){
        Preconditions.checkArgument(!StringUtils.isEmpty(mode),"不允许输入空字符串");
        return strategyMap.get(mode);
    }
}
