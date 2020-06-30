package com.dousnl.utils;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;


/**
 * 布隆过滤器
 *
 * 1.可通过redssion操作redis实现
 * 2.可通过google guava工具实现
 * @author hanliang
 * @version 1.0
 * @date 2020/6/30 13:43
 */
public class BloomFilterTest {

    public static void main(String[] args) {
        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), 100000, 0.01);
        long start=System.currentTimeMillis();
        System.out.println("start:"+start+"ms");
        bloomFilter.put("13162505297");

        System.out.println(bloomFilter.mightContain("123456"));
        System.out.println(bloomFilter.mightContain("13162505297"));
        long end=System.currentTimeMillis();
        System.out.println("end:"+end+"ms");
        System.out.println((end-start)+"ms");
    }
}
