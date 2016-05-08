package kasogg.com.gankcenter.ui.base;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;

import kasogg.com.gankcenter.R;

public abstract class BaseToolbarActivity extends BaseActivity {
    protected AppBarLayout mAppBar;
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewResId());
        mAppBar = (AppBarLayout) findViewById(R.id.app_bar_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar == null || mAppBar == null) {
            throw new IllegalStateException("The subclass of BaseToolbarActivity must contain a toolbar.");
        }
        setSupportActionBar(mToolbar);
    }

    protected abstract int getContentViewResId();

}
