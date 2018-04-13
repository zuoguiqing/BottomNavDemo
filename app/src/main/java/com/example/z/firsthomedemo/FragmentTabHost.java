package com.example.z.firsthomedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.example.z.firsthomedemo.fragment.FragmentAct;
import com.example.z.firsthomedemo.fragment.FragmentHome;
import com.example.z.firsthomedemo.fragment.FragmentMe;
import com.example.z.firsthomedemo.fragment.FragmentVideo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zgq
 * @version 1.0
 * @describe --
 * @date 2018/4/13
 */
public class FragmentTabHost extends AppCompatActivity {

    @BindView(R.id.frameLayout_nr)
    FrameLayout frameLayout_nr;
    @BindView(R.id.rg_main_tab)
    RadioGroup radioGroup;

    private Class fragments[];
    private android.support.v4.app.FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_tab_host);
        ButterKnife.bind(this);
        initialize();
    }

    private void initialize() {
        fragments = new Class[]{ FragmentHome.class,FragmentAct.class, FragmentVideo.class, FragmentMe.class};
        tabHost = findViewById(R.id.tab);
        tabHost.setup(getApplicationContext(), getSupportFragmentManager(), R.id.frameLayout_nr);  //tabHost关联上下文，FragmentManager和显示区域
        for (int i = 0; i < fragments.length; i++) {   //向TabHost中添加fragment和标志位
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(String.valueOf(i)).setIndicator(String.valueOf(i));
            tabHost.addTab(tabSpec, fragments[i], null);
        }
        tabHost.setCurrentTab(0);//设置初始选中项
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        tabHost.setCurrentTab(0);
                        break;
                    case R.id.rb_act:
                        tabHost.setCurrentTab(1);
                        break;
                    case R.id.rb_video:
                        tabHost.setCurrentTab(2);
                        break;
                    case R.id.rb_me:
                        tabHost.setCurrentTab(3);
                        break;
                }
            }
        });
    }
}
