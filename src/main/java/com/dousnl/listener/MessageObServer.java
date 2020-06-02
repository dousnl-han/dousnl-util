package com.dousnl.listener;

import org.springframework.stereotype.Component;

import java.util.Observable;
import java.util.Observer;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/5/13 11:52
 */
@Component
public class MessageObServer implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(">>>>收到消息："+arg);
    }
}
