package kasogg.com.gankcenter.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import kasogg.com.gankcenter.R;
import kasogg.com.gankcenter.api.ApiFactory;
import kasogg.com.gankcenter.entity.MeiZhiData;
import kasogg.com.gankcenter.entity.Result;
import kasogg.com.gankcenter.ui.base.BaseToolbarActivity;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApiFactory.getGankApi().listRepos(10, 1).map(new Func1<Result<MeiZhiData>, List<MeiZhiData>>() {
            @Override
            public List<MeiZhiData> call(Result<MeiZhiData> results) {
                return results.results;
            }
        }).flatMap(new Func1<List<MeiZhiData>, Observable<MeiZhiData>>() {
            @Override
            public Observable<MeiZhiData> call(List<MeiZhiData> strings) {
                return Observable.from(strings);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<MeiZhiData>() {
            @Override
            public void call(MeiZhiData data) {
                Log.d("TAG", data.url + "\n");
            }
        });
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
