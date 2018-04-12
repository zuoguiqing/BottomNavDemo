package com.example.z.firsthomedemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.z.firsthomedemo.adapter.MainPagerAdp;
import com.example.z.firsthomedemo.fragment.FragmentAct;
import com.example.z.firsthomedemo.fragment.FragmentHome;
import com.example.z.firsthomedemo.fragment.FragmentMe;
import com.example.z.firsthomedemo.fragment.FragmentVideo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
*  @describe  -- 能滑动的
*  @author zgq
*  @version 1.0
*  @date 2018/4/4
*/
public class SlideMainActivity extends AppCompatActivity {


    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.rg_main_tab)
    RadioGroup rg;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_main);
        ButterKnife.bind(this);

        initFragment();
        setListener();
        setPage(1);
    }


    /**
     * 初始化fragment
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new FragmentHome());
        fragments.add(new FragmentAct());
        fragments.add(new FragmentVideo());
        fragments.add(new FragmentMe());
        vp.setAdapter(new MainPagerAdp(getSupportFragmentManager(), fragments));
        vp.setOffscreenPageLimit(4);

    }

    /**
     * 设置选中页数
     */
    private void setPage(int page) {
        RadioButton rb = (RadioButton) rg.getChildAt(page);
        rb.setChecked(true);
        vp.setCurrentItem(page);
    }

    /**
     * 设置底部菜单的监听
     */
    private void setListener() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < group.getChildCount(); i++) {
                    RadioButton rb = (RadioButton) group.getChildAt(i);
                    if (rb.isChecked()) {
                        vp.setCurrentItem(i);
                        break;
                    }
                }
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                RadioButton rb = (RadioButton) rg.getChildAt(position);
                rb.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


}
