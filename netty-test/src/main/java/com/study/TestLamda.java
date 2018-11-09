package com.study;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin
 * @Title: TestLamda
 * @ProjectName netty-im-chat
 * @Description: TODO
 * @date 2018/10/27 11:34
 */
public class TestLamda {
    public static void main(String[] args) {
        List<Integer> lists = new ArrayList<Integer>();

        lists.add(1);
        lists.add(2);
        lists.add(3);
        lists.add(4);
        lists.add(5);
        lists.add(6);
        lists.add(7);
        lists.add(8);



//        lists.forEach((t)->{
//            System.out.println(t);
//        });

//        lists.parallelStream().forEach(TestLamda::deal);
        for (Integer i: lists) {
            deal(i);
        }
    }

    public static void deal(Integer i){
        try {
            System.out.println(i);
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
