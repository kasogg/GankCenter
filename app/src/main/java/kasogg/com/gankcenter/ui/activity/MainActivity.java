package kasogg.com.gankcenter.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kasogg.com.gankcenter.R;
import kasogg.com.gankcenter.api.ApiFactory;
import kasogg.com.gankcenter.entity.Meizhi;
import kasogg.com.gankcenter.entity.MeizhiResult;
import kasogg.com.gankcenter.ui.adapter.MeizhiListAdapter;
import kasogg.com.gankcenter.ui.base.BaseToolbarActivity;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseToolbarActivity {
    Dialog dialog;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    MeizhiListAdapter mAdapter;
    List<Meizhi> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initViews();
        getData();
    }

    private void initViews() {
        dialog = ProgressDialog.show(this, null, "加载中");
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new MeizhiListAdapter(this, mDataList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_main;
    }

    public void getData() {
        dialog.show();
        ApiFactory.getGankApi().listRepos(20, 1).map(new Func1<MeizhiResult, List<Meizhi>>() {
            @Override
            public List<Meizhi> call(MeizhiResult meizhiResult) {
                return meizhiResult.results;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doAfterTerminate(new Action0() {
            @Override
            public void call() {
                dialog.dismiss();
            }
        }).doOnNext(new Action1<List<Meizhi>>() {
            @Override
            public void call(List<Meizhi> meizhis) {
                for (Meizhi meizhi : meizhis) {
                    Log.i("TAG", meizhi.url);
                }
            }
        }).subscribe(new Action1<List<Meizhi>>() {
            @Override
            public void call(List<Meizhi> meizhis) {
                mAdapter.setAndRefresh(meizhis);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Toast.makeText(getApplicationContext(), "加载失败", Toast.LENGTH_SHORT).show();
            }
        });
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

    @OnClick(R.id.recyclerView)
    public void onClick() {
    }
}
