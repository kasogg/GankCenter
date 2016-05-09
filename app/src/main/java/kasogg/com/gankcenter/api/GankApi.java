package kasogg.com.gankcenter.api;

import kasogg.com.gankcenter.entity.MeizhiResult;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by KasoGG on 2016/5/8.
 */
public interface GankApi {
    @GET("data/福利/{pageSize}/{pageIndex}")
    Observable<MeizhiResult> listRepos(@Path("pageSize") int pageSize, @Path("pageIndex") int pageIndex);
}
