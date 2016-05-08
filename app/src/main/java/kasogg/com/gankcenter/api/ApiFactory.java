package kasogg.com.gankcenter.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KasoGG on 2016/5/8.
 */
public class ApiFactory {
    public static final String SERVER_URL = "http://gank.io/api";
    private static Retrofit retrofit;

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(SERVER_URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory
                    (RxJavaCallAdapterFactory.create()).build();
        }
        return retrofit;
    }

    public static GankApi getGankApi() {
        return null;
    }
}
