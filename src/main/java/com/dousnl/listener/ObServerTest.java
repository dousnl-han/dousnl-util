package com.dousnl.listener;

import java.util.Observable;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/5/13 13:18
 */
public class ObServerTest {

    public static void main(String[] args) {

        Observable note=new NoteObserverable();
        note.addObserver(new MessageObServer());
        note.notifyObservers("发送消息111");
        Integer i=01;
        System.out.println(i);

        Observable shop = new ShopObservable();
        shop.addObserver(new MessageObServer());
        shop.notifyObservers("提交订单");

    }
}
