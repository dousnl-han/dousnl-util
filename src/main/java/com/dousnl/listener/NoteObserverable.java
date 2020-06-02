package com.dousnl.listener;

import java.util.Observable;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/5/13 11:51
 */
public class NoteObserverable extends Observable {

    @Override
    public void notifyObservers(Object arg) {
        System.out.println("通知："+arg);
        super.setChanged();
        System.out.println("状态是否改变："+hasChanged());
        super.notifyObservers(arg);
    }
}
