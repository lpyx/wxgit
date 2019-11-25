package com.httpkeepalive;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RNOkHttpClient {
    public static OkHttpClient createClient() {

        return createClientBuilder().build();
    }

    public static OkHttpClient.Builder createClientBuilder() {
        // No timeouts by default
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .connectTimeout(0, TimeUnit.MILLISECONDS)
                .readTimeout(0, TimeUnit.MILLISECONDS)
                .writeTimeout(0, TimeUnit.MILLISECONDS)
                .cookieJar(new ReactCookieJarContainer());

       // return enableTls12OnPreLollipop(client);
        return client;
    }

    /*
      On Android 4.1-4.4 (API level 16 to 19) TLS 1.1 and 1.2 are
      available but not enabled by default. The following method
      enables it.
     */
    public static OkHttpClient.Builder enableTls12OnPreLollipop(OkHttpClient.Builder client) {
            try {
                client.sslSocketFactory(new TLSSocketFactory());

                ConnectionSpec cs = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                        .tlsVersions(TlsVersion.TLS_1_2)
                        .build();

                List<ConnectionSpec> specs = new ArrayList<>();
                specs.add(cs);
                specs.add(ConnectionSpec.COMPATIBLE_TLS);
                specs.add(ConnectionSpec.CLEARTEXT);

                client.connectionSpecs(specs);
            } catch (Exception exc) {
                exc.printStackTrace();
            }

        return client;
    }

}
