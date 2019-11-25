package com.sop;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.l.pyx.HttpUtils;
import com.sun.net.httpserver.Headers;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TestDetail {
    static Gson gson = new Gson();
    public static void main(String[] args) {
        Executor exec = Executors.newFixedThreadPool(50);
        Map a = new HashMap<String,String>();

        a.put("X-Org-Id","186");
        a.put("X-User-Id","12046");
        a.put("X-User-Name","1221");
//        String detailUurl  = "http://sopexe-feature.test.blacklake.tech/v1/_offline/{id}/_detail";
//        String listUrl  = "http://sopexe-feature.test.blacklake.tech/v1/_offline/_list?maxTaskCount=100";
//
//        list(listUrl,detailUurl, a);
        String subDetailUrl  = "http://sopexe-feature.test.blacklake.tech/v1/_offline/{id}/_detail_sub";
        getDetailLength(subDetailUrl, 365L, a);
        //headers.add("X-Org-Id","94");
       // sendGet(url,a);
//        for(int i=0;i<1000;i++){
          //  httpGetSend(url,a);
//            System.out.println(i);
//        }
//        for(int i=0;i<1000;i++){
//            exec.execute(new Runnable() {
//                @Override
//                public void run() {
//                    httpGetSend(url,a);
//                    System.out.println("ffff");
//                }
//            });
//        }

    }

    public static void list(String listUrl,String detailUrl, Map<String,String> header){
        String a = httpGetSend(listUrl, header);
        Map map = gson.fromJson(a,Map.class);
        ArrayList dataList =(ArrayList) map.get("data");
        if(dataList != null) {
            for (int i = 0; i < dataList.size(); i++) {
                Map b=  (Map)dataList.get(i);
                String id =((Double)(b.get("sopTaskId"))).longValue()+"";
                String realUrl = detailUrl.replace("{id}",id);
                String detail = httpGetSend(realUrl, header);
                System.out.printf(id);
                System.out.printf("  ");
                System.out.printf(detail.length()+"");
                System.out.println();
            }
        }
        System.out.printf("");
    }


    private static void getDetailLength(String getUrl, Long id, Map<String,String> header){
        String[] subArray = new String[]{
                "total",
                "task",
                "steps",
                "stepList",
                "taskHistoryList",
                "manufactureDTO",
                "weighingDTO"
        };
        for(int i=0;i<subArray.length;i++){
            getDetail(getUrl, id, subArray[i], header);
        }
    }


    private static void  getDetail(String getUrl, Long id, String sub, Map<String,String> header) {
        String detail = httpGetSend(getUrl.replace("{id}", id+"") + "?sub="+sub, header);
        System.out.printf(sub);
        System.out.printf(" ");
        try {
            System.out.println(detail.getBytes("UTF-8").length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    private static void printStr(String name, String o){
        //System.out.println(gson.toJson(o));
        System.out.printf(name);
        System.out.printf(" ");
        if(o == null){
            System.out.println(0);
        }else{
            try {
                System.out.println(o.getBytes("UTF-8").length);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

    }

    public static String httpGetSend(String getUrl,Map<String,String> header) {
        URL url = null;
        InputStream in = null;
        HttpURLConnection conn = null;

        try {
            url = new URL(getUrl);
            conn = (HttpURLConnection) url.openConnection();
            //setHeader(conn, orgId);
            Iterator<String> ite = header.keySet().iterator();
            while(ite.hasNext()){
                String key = ite.next();
                conn.setRequestProperty(key, header.get(key));
            }
            conn.setConnectTimeout(2000);
            conn.setReadTimeout(300000);
            //printlnHeader(conn);


            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                in = conn.getInputStream();
                byte[] bytes = new byte[1024];
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int len = in.read(bytes);
                while (len > -1) {
                    bos.write(bytes, 0, len);
                    len = in.read(bytes);
                }
                saveToFile("/Users/handty/td/1",bos.toByteArray());
                //System.out.println("fff");
                String str = bos.toString("UTF-8");

               // System.out.println(str);
                return str;
            } else {
                in = conn.getErrorStream();
                byte[] bytes = new byte[1024];
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int len = in.read(bytes);
                while (len > -1) {
                    bos.write(bytes, 0, len);
                    len = in.read(bytes);
                }
                String str = bos.toString("UTF-8");

               System.out.println(str);
                throw new RuntimeException("请求失败");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
            }
        }
    }


    private static void saveToFile(String filePath,byte[] bytes){
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);

        }catch (Exception e){

        }
    }
}
