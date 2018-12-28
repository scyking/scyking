package com.example.demo.thread;

import com.example.demo.enums.HttpRequestType;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName HttpThreadRunImpl
 * @Description 异步“发送http请求”任务
 * @Author scyking
 * @Date 2018/11/22 8:56
 * @Version 1.0
 */
public class HttpThreadRunImpl implements Runnable {

    // 发送请求次数
    Integer num;

    // 请求链接地址
    String url;

    public HttpThreadRunImpl(String url, Integer num) {
        this.num = num;
        this.url = url;
    }

    @Override
    public void run() {
        // 传参合法性校验
        if (isValidity(url, num)) {
            // TODO 异步请求
            // 最大连接数只有10，单主机的最大连接数只有5
            // CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
            // 手动设置最大连接数以及单主机的连接数
            CloseableHttpAsyncClient httpclient = HttpAsyncClientBuilder.create()
                    .setMaxConnTotal(20000).setMaxConnPerRoute(20000).build();
            httpclient.start();
            long startTime = System.currentTimeMillis();
            final CountDownLatch latch = new CountDownLatch(num);
            while (num > 0) {
                num--;
                HttpGet request = new HttpGet(url);
                httpclient.execute(request, new FutureCallback<HttpResponse>() {
                    public void completed(final HttpResponse response) {
                        latch.countDown();
//                        System.out.println(" callback thread id is : " + Thread.currentThread().getId());
//                        System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
//                        try {
//                            String content = EntityUtils.toString(response.getEntity(), "UTF-8");
//                            System.out.println(" response content is : " + content);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
                    }

                    public void failed(final Exception ex) {
                        latch.countDown();
//                        System.out.println(request.getRequestLine() + "->" + ex);
//                        System.out.println(" callback thread id is : " + Thread.currentThread().getId());
                    }

                    public void cancelled() {
                        latch.countDown();
//                        System.out.println(request.getRequestLine() + " cancelled");
//                        System.out.println(" callback thread id is : " + Thread.currentThread().getId());
                    }
                });
            }
            System.out.println("==============================> 发送花费时间：" + String.valueOf(System.currentTimeMillis() - startTime));
            try {
                latch.await();
                System.out.println("==============================> 请求花费时间：" + String.valueOf(System.currentTimeMillis() - startTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                httpclient.close();
            } catch (IOException ignore) {
                ignore.printStackTrace();
            }
        }
    }

    /**
     * 数据合法性校验（目前仅做非空、数值合法性判断）
     *
     * @param url
     * @param num
     * @return
     */
    private boolean isValidity(String url, Integer num) {
        boolean flag = true;
        if (null == num || num < 1 || StringUtils.isEmpty(url) || !StringUtils.startsWithIgnoreCase(url, "http")) {
            System.out.println("-------------------------> 传参非法 ");
            System.out.println("-------------------------> 请求URL(需要协议头,如“https”)：" + url);
            System.out.println("-------------------------> 请求次数(需大于0整数)：" + num);
            flag = false;
        }
        return flag;
    }
}
