package com.httpkeepalive;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;

public class KeepAlive {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                sendStatic("https://api2-test.blacklake.cn/info");
               // sendStatic("http://10.1.30.7:8080/cat/r/home?op=view&docName=index");
                Thread.sleep(1000000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //httpGetSend("https://api2-test.blacklake.cn/info");

        }
    }

//    public static String httpGetSend(String getUrl) {
//        URL url = null;
//        InputStream in = null;
//        URLConnection conn = null;
//
//        try {
//            url = new URL(getUrl);
//            conn = url.openConnection();
//
//            conn.setConnectTimeout(200000);
//            conn.setReadTimeout(300000);
//            conn.setRequestProperty("Connection", "Keep-Alive: timeout=50000, max=100");
//            in = conn.getInputStream();
//            StringBuilder sb = new StringBuilder();
//            ByteArrayOutputStream bi = new ByteArrayOutputStream();
//            byte[] bytes = new byte[1024];
//            int length = in.read(bytes);
//            while (length > 0) {
//                bi.write(bytes, 0, length);
//                length = in.read(bytes);
//            }
//            sb.append(new String(bi.toByteArray(), "UTF-8")).append("");
//
//            System.out.println(sb.toString());
//            return sb.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            try {
//                if (in != null) {
//                    in.close();
//                }
//            } catch (IOException e) {
//            }
//        }
//    }

    static URLConnection staticConnection = null;

    private static void getConnection(String getUrl) {
        URL url = null;
        InputStream in = null;
        URLConnection conn = null;

        try {
            url = new URL(getUrl);
            conn = url.openConnection();

            conn.setConnectTimeout(2000);
            conn.setReadTimeout(3000);
            conn.setDoOutput(true);
            //conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Connection", "Keep-Alive:timeout=10");
            conn.setRequestProperty("Keep-Alive", "timeout=10");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        staticConnection = conn;
    }

    private static void sendStatic(String getUrl) {
        InputStream in = null;
        OutputStream os = null;
        try {
            if (staticConnection == null) {
                getConnection(getUrl);
            }
            //os = staticConnection.getOutputStream();
            //os.write("".getBytes());
            in = staticConnection.getInputStream();

            StringBuilder sb = new StringBuilder();
            ByteArrayOutputStream bi = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int length = in.read(bytes);



            while (length > 0) {
                bi.write(bytes, 0, length);
                length = in.read(bytes);
            }
            sb.append(new String(bi.toByteArray(), "UTF-8")).append("");

            System.out.println(sb.toString());
            Thread.sleep(1000000);
            // return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            //return null;
        } finally {
//            try {
//                if (in != null) {
//                    in.close();
//                }
//            } catch (IOException e) {
//            }
        }
    }
}
