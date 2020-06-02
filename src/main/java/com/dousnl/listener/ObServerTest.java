package com.dousnl.listener;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/5/13 13:18
 */
public class ObServerTest {

    public static void main(String[] args) {

        NoteObserverable note=new NoteObserverable();
        System.out.println("状态是否改变："+note.hasChanged());
        note.addObserver(new MessageObServer());
        note.notifyObservers("发送消息111");
        System.out.println("状态是否改变："+note.hasChanged());
        Integer i=01;
        Integer i1=02;
        System.out.println(i);
    }
}
