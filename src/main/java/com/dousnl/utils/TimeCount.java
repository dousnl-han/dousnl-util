package com.dousnl.utils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TimeCount {

    private AtomicLong time;

    private AtomicInteger count;

    public TimeCount(){
        this.time = new AtomicLong();
        this.count = new AtomicInteger();
    }

    private int addCount(long countTime){
        long oldTime = time.get();
        if(countTime!=oldTime&&countTime>oldTime){
            boolean updateTime = time.compareAndSet(oldTime,countTime);
            if(updateTime){
                count.set(0);
            }
        }
        return count.incrementAndGet();
    }

    /**
     * 1秒内的调用量
     * @return
     */
    public int getSecondCount(){
        long second = System.currentTimeMillis()/1000;
        return addCount(second);
    }

}
