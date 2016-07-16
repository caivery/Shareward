package scu.miomin.com.shareward.sample.activity;

import android.os.Bundle;

import com.scu.miomin.sharewardlib.constants.ActivityType;
import com.scu.miomin.sharewardlib.core.BaseFragment;
import com.scu.miomin.sharewardlib.toolbar.ToolbarActivity;
import com.scu.miomin.sharewardlib.widgets.tab.ShareTabLayout;
import com.scu.miomin.sharewardlib.widgets.tab.ShareTabView;

import java.util.ArrayList;

import scu.miomin.com.shareward.R;
import scu.miomin.com.shareward.sample.fragment.SampleFragment;

/**
 * Created by 莫绪旻 on 16/7/15.
 */
public class SampleTabActivity extends ToolbarActivity implements ShareTabLayout.OnTabClickListener {

    private ShareTabLayout mTabLayout;
    private BaseFragment fragment;
    private ArrayList<ShareTabView.Tab> tabs = new ArrayList<>();

    @Override
    protected void getContentView() {
        setContentView(R.layout.activity_sample_tab, ActivityType.MODE_TOOLBAR_BACK);
    }

    @Override
    protected void setUpView() {
        mTabLayout = (ShareTabLayout) findViewById(R.id.mTabLayout);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        tabs.add(new ShareTabView.Tab(R.drawable.tab_main_msg, R.string.tab_label_msg, SampleFragment.class));
        tabs.add(new ShareTabView.Tab(R.drawable.tab_main_home, R.string.tab_label_home, SampleFragment.class));
        tabs.add(new ShareTabView.Tab(R.drawable.tab_main_setting, R.string.tab_label_setting, SampleFragment.class));

        if (tabs.size() <= 0) {
            finish();
            return;
        }

        mTabLayout.setUpData(tabs, this);
        mTabLayout.setCurrentTab(0);
    }

    /**
     * 监听切换到的tab
     */
    @Override
    public void onTabSelected(int index) {
        try {
            fragment = tabs.get(index).targetFragmentClz.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.mFragmentContainerLayout, fragment).commitAllowingStateLoss();
            setUpTitle(getResources().getString(tabs.get(index).labelResId));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听被取消选中的tab
     */
    @Override
    public void onTabChangeToUnSelected(int index) {

    }
}
