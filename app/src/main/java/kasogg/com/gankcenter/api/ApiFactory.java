package kasogg.com.gankcenter.api;

import kasogg.com.gankcenter.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KasoGG on 2016/5/8.
 */
public class ApiFactory {
    public static final String SERVER_URL = "http://gank.io/api/";
    private GankApi gankApi;

    // @formatter:off
    private ApiFactory() {
        OkHttpClient client = buildOKHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        gankApi = retrofit.create(GankApi.class);
    }
    // @formatter:on

    private OkHttpClient buildOKHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        return builder.build();
    }

    public static GankApi getGankApi() {
        return Nested.instance.gankApi;
    }

    static class Nested {
        private static ApiFactory instance = new ApiFactory();
    }

}
