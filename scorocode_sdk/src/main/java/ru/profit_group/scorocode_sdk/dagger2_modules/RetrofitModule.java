package ru.profit_group.scorocode_sdk.dagger2_modules;

import java.security.cert.CertificateException;
import java.util.Collections;

import javax.inject.Scope;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import okhttp3.CipherSuite;
import okhttp3.Connection;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.profit_group.scorocode_sdk.BuildConfig;
import ru.profit_group.scorocode_sdk.dagger2_scopes.SingletonScope;

import static ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder.getBaseURL;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

@Module
public class RetrofitModule {


    @Provides
    @SingletonScope
    public Retrofit retrofit(OkHttpClient client, RxJavaCallAdapterFactory rxJavaCallAdapterFactory, GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(getBaseURL())
                .client(client)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    @SingletonScope
    public OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor, SSLSocketFactory sslSocketFactory, HostnameVerifier hostnameVerifier) {
        return new OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory)
                .hostnameVerifier(hostnameVerifier)
                .addInterceptor(loggingInterceptor)
                .followRedirects(false)
                .build();

    }

    @Provides
    public HostnameVerifier hostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
    }

    @Provides
    @SingletonScope
    public SSLSocketFactory sslSocketFactory() {
        SSLSocketFactory sslSocketFactory = null;
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {}

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {}

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sslSocketFactory;
    }

    @Provides
    @SingletonScope
    public HttpLoggingInterceptor loggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);

        return loggingInterceptor;
    }

    @Provides
    @SingletonScope
    public RxJavaCallAdapterFactory rxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @Provides
    @SingletonScope
    public GsonConverterFactory gsonConverterFactory() {
        return GsonConverterFactory.create();
    }
}
