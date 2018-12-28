package com.example.demo;

import com.example.demo.enums.HttpRequestType;
import com.example.demo.thread.HttpThreadRunImpl;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName TestMain
 * @Description TODO
 * @Author scyking
 * @Date 2018/11/22 9:45
 * @Version 1.0
 */
public class TestMain {

    // 测试地址
    static final String testUrl = "http://www.baidu.com";

    public static void main(String[] args) {
        String urlTmp = testUrl;
        int numTmp = 1000;
        if (args.length > 1) {
            urlTmp = args[0];
            numTmp = Integer.valueOf(args[1]);
        }
        System.out.println("测试url地址为：" + urlTmp);
        System.out.println("发送请求次数：" + numTmp);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new HttpThreadRunImpl(urlTmp, numTmp));
//        exec.execute(new HttpThreadRunImpl(url, 10));
//        exec.execute(new HttpThreadRunImpl(url, 10));
        exec.shutdown();

    }
}
