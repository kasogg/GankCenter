package kasogg.com.gankcenter.api;

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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        gankApi = retrofit.create(GankApi.class);
    }
    // @formatter:on

    public static GankApi getGankApi() {
        return Nested.instance.gankApi;
    }

    static class Nested {
        private static ApiFactory instance = new ApiFactory();
    }

}
