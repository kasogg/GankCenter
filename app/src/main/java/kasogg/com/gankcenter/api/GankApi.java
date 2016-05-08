package kasogg.com.gankcenter.api;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by KasoGG on 2016/5/8.
 */
public interface GankApi {

    @GET("users/{user}/repos")
    Observable<List<String>> listRepos(@Path("user") String user);

}
