package com.l.pyx;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpUtils {
    private static String ORG_ID_HEADER_NAME = "X-Org-Id";
    private static String USER_ID_HEADER_NAME = "X-User-Id";
    private static String USER_NAME_HEADER_NAME = "X-User-Name";

    public static String httpPostSend(Long orgId, String urlPrefix, String content,Map<String,String> header) {
        URL url = null;
        InputStream in = null;
        OutputStreamWriter writer = null;
        HttpURLConnection conn = null;

        try {
            url = new URL(urlPrefix);
            conn = (HttpURLConnection) url.openConnection();
            setHeader(conn, orgId);
            Iterator<String> ite = header.keySet().iterator();
            while(ite.hasNext()){
                String key = ite.next();
                conn.setRequestProperty(key, header.get(key));
            }
            conn.setConnectTimeout(2000);
            conn.setReadTimeout(300000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            writer = new OutputStreamWriter(conn.getOutputStream());

            writer.write(content);
            writer.flush();

            printlnHeader(conn);
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                in = conn.getInputStream();
                byte[] bytes = new byte[1024];
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int len = in.read(bytes);
                while (len > -1) {
                    bos.write(bytes, 0, len);
                    len = in.read(bytes);
                }
                String str = bos.toString("UTF-8");

                System.out.println(str);

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
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
            }
        }
    }

    private static void  printlnHeader(URLConnection conn){
        Map<String, List<String>> map =  conn.getHeaderFields();
        Iterator<String> ite = map.keySet().iterator();
        while(ite.hasNext()){
            String a = ite.next();
            System.out.print(a);
            System.out.printf("-->");
            System.out.println(map.get(a));
        }


    }

    public static String httpGetSend(Long orgId, String getUrl,Map<String,String> header) {
        URL url = null;
        InputStream in = null;
        HttpURLConnection conn = null;

        try {
            url = new URL(getUrl);
            conn = (HttpURLConnection) url.openConnection();
            setHeader(conn, orgId);
            Iterator<String> ite = header.keySet().iterator();
            while(ite.hasNext()){
                String key = ite.next();
                conn.setRequestProperty(key, header.get(key));
            }
            conn.setConnectTimeout(2000);
            conn.setReadTimeout(300000);
            printlnHeader(conn);


            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                in = conn.getInputStream();
                byte[] bytes = new byte[1024];
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int len = in.read(bytes);
                while (len > -1) {
                    bos.write(bytes, 0, len);
                    len = in.read(bytes);
                }
                File file = new File("/Users/handty/a.jpeg");
                file.createNewFile();
                FileOutputStream fos = new FileOutputStream("/Users/handty/a.jpeg");

                fos.write(bos.toByteArray());
                String str = bos.toString("UTF-8");

                System.out.println(str);
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

    public static void main(String[] args) {
        httpGetSend(3L,"https://gc-attachment.s3.cn-north-1.amazonaws.com.cn/%E5%9B%BE%E7%89%874-1524461218559.jpeg",new HashMap());
    }

//    public static void main(String[] args) {
//        String ex = "";
//        String s = null;
//        System.out.printf("************开始导出数据");
//        try {
//           s  = httpGetSend(126L, "http://sopdef-dev.test.blacklake.tech/v1/_export/_export_sop?sopId=63");
//        }catch (Exception e){
//            ex = e.getMessage();
//        }
//        if(s == null || s.isEmpty()){
//            System.out.printf("************导出数据失败:"+ex);
//        }else {
//            System.out.printf("************导出数据成功，开始导入");
//            try {
//                String importS = httpPostSend(2L, "http://sopdef-dev.test.blacklake.tech/v1/_import/_import_sop?sopId=63", s);
//                System.out.printf("************导入数据成功");
//            }catch (Exception ex1){
//                System.out.printf("************导入数据失败:"+ex1.getMessage());
//            }
//        }
//    }

    private static void setHeader(URLConnection conn, Long orgId) {
        conn.setRequestProperty(ORG_ID_HEADER_NAME, orgId.toString());
        conn.setRequestProperty(USER_ID_HEADER_NAME, "0");
        conn.setRequestProperty(USER_NAME_HEADER_NAME, "");
    }

}
