package com.dousnl.strategy;

import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/6/30 14:46
 */
@Component
public class MultiplyStrategy implements CalculateStrategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1*num2;
    }
}
