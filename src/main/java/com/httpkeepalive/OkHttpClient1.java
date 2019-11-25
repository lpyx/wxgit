package com.httpkeepalive;

import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OkHttpClient1 {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    static OkHttpClient okHttpClient;

    /**
     * 获取客户端
     * @return
     */
    private static OkHttpClient getClient(){
        if(okHttpClient == null) {

            List<Protocol> protocols = new ArrayList<>();
            //protocols.add(Protocol.HTTP_2);
            protocols.add(Protocol.HTTP_1_1);
            OkHttpClient client1 = new OkHttpClient.Builder().protocols(
                    protocols
            ).build();
            //okHttpClient = client1;
           //H okHttpClient = RNOkHttpClient.createClient();
            okHttpClient = new OkHttpClient.Builder().build();
        }
        return okHttpClient;

    }


    static String get(String url)throws IOException{
        Request request = new Request.Builder().url(url).get().build();
        Response response = getClient().newCall(request).execute();
        return response.body().string();
    }






    static String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(


                        body)
                .build();
        Response response = getClient().newCall(request).execute();
        return response.body().string();
    }
    static class MyRunnable implements Runnable{
        @Override
        public void run() {
            try {
                post("http://manufacture.blacklake.tech/v1/materialLot/_test_insert_m?count=100","{}");
                System.out.printf("over");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        init();
        for(int i=0;i<10;i++){
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }

        try {
            Thread.sleep(200000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        try {
//            for (int i = 0; i < 100; i++) {
//                System.out.println(i);
//                post("http://manufacture.blacklake.tech/v1/materialLot/_test_clear","{}");
//                System.out.println("clear");
//                post("http://manufacture.blacklake.tech/v1/materialLot/_test_insert","{}");
//                System.out.println("insert");
//
//                post("http://manufacture.blacklake.tech/v1/materialLot/_test_insert_m?count=100","{}");
//                System.out.println("insertM");
//                //sendGet("https://api2.blacklake.cn/info");
//                //Thread.sleep(5000);
//                //System.out.println(get("https://api2-test.blacklake.cn/manufacture/v1/reproduce_tasks?page=1&size=20&status=1,2"));
//                //System.out.println(get("https://api2-test.blacklake.cn/info"));
//                //sendGet("https://api2.blacklake.cn/info");
//                //sendGet("https://api2.blacklake.cn/ab_shipment/v1/receive_task/_list_for_app?searchStep=0,1,2,3,4,5,6,7,9,10,11,15&page=1");
//                //sendGet("https://api2-test.blacklake.cn/ab_shipment/v1/receive_task/_list_for_app?k=test");
//                // sendGet("http://10.1.30.7:8080/cat/r/home?op=view&docName=index");
//                //sendGet("http://cat.blacklake.tech/cat/r/home?op=view&docName=index");
//                //sendGet("https://qyapi.weixin.qq.com");
//
//               // Thread.sleep(100);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//
//        } finally {
//
//        }
    }

    static void init() {
        try {
            post("http://manufacture.blacklake.tech/v1/materialLot/_test_clear", "{}");
            System.out.println("clear");
            post("http://manufacture.blacklake.tech/v1/materialLot/_test_insert", "{}");
            System.out.println("insert");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
