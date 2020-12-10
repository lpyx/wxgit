package com.httpkeepalive;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HttpPool {


    public static void main(String[] args) {
        try {
            sendGet("https://baidu.com");

        } catch (Exception e) {
            e.printStackTrace();


        } finally {
//            try {
//                httpClient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

    static CloseableHttpClient httpClient = null;

    public static synchronized CloseableHttpClient getHttpClient() {
        if (httpClient == null) {
            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
            // 连接池最大连接数
            cm.setMaxTotal(200);
            // 单条链路最大连接数（一个ip+一个端口 是一个链路）



            cm.setDefaultMaxPerRoute(100);
            // 指定某条链路的最大连接数

            ConnectionKeepAliveStrategy kaStrategy = new DefaultConnectionKeepAliveStrategy() {
                @Override
                public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                    long keepAlive = super.getKeepAliveDuration(response, context);
                    System.out.println(keepAlive);
                    if (keepAlive == -1) {
                        keepAlive = 2100000;
                    }
                    return keepAlive;
                }

            };
            //设置 evictExpiredConnections 后，每隔10s清理过期链接
            httpClient = HttpClients.createDefault();
            // httpClient = HttpClients.custom().setConnectionManager(cm).setKeepAliveStrategy(kaStrategy).evictExpiredConnections().build();
            //httpClient = HttpClients.custom().setConnectionManager(cm).build();
        }

        return httpClient;
    }

    private static int CONNECT_TIMEOUT = 10000;
    private static int SOCKE_TTIMEOUT = 10000;
    private static int CONNECTION_REQUEST_TIMEOUT = 1000;

    public static void sendGet(String url) {

        HttpGet httpGet = new HttpGet(url);
        //httpGet.setProtocolVersion(new HttpVersion(2,0));
      //  httpGet.setProtocolVersion(new ProtocolVersion("HTTPS",2,0));
       // Connection:
        //httpGet.setHeader("Connection", "close");
        httpGet.setHeader("x-device-id", "test1");
        httpGet.setHeader("x-client", "30");
        httpGet.setHeader("x-client-version", "2.19.2");
        httpGet.setHeader("x-devie-brand", "Honeywell");
        httpGet.setHeader("x-devie-time", "1543914445021");
        httpGet.setHeader("x-devie-model", "EDA70");
        httpGet.setHeader(":scheme", "https");
        httpGet.setHeader(":authority", "api2-test.blacklake.cn");
        httpGet.setHeader("user-agent", "okhttp/3.10.0");
        httpGet.setHeader("accept-encoding", "gzip");
        httpGet.setHeader("origin", "https://web2-test.blacklake.cn");
        httpGet.setHeader("X-AUTH", "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjE2LCJzc29Ub2tlbiI6Iml2Mk9tT0s3Q01kVjR6Vm0ifQ.shg1NAlxlbaN7W6OKr8gfmnbal3V_Xo7wMkzDa1wtCjpdR07dD0FMZtihCTN6aKHGLMgKZiaYSiJFtACNInDqQ");
        // httpGet.setHeader("","timeout:5");
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                .setSocketTimeout(SOCKE_TTIMEOUT).build();
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = null;



        try {
            response = getHttpClient().execute(httpGet);

            Integer code = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            System.out.println(entity.getContent().toString());

            ByteArrayOutputStream bi = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            InputStream is = entity.getContent();
            int length = entity.getContent().read(bytes);
            while (length > 0) {
                bi.write(bytes, 0, length);
                length = is.read(bytes);
            }
            System.out.println((new String(bi.toByteArray(), "UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
         //   httpGet.releaseConnection();
        }
    }
}
