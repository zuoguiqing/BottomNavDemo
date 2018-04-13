package com.example.z.firsthomedemo;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.z.firsthomedemo.fragment.FragmentAct;
import com.example.z.firsthomedemo.fragment.FragmentHome;
import com.example.z.firsthomedemo.fragment.FragmentMe;
import com.example.z.firsthomedemo.fragment.FragmentVideo;
import com.longe.myresource.base.MyBaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
*  @describe  -- 不带滑动的 注：此方式有缺陷，通过onBack按键销毁时，会执行onSaveInstanceState
 *  之后会有异常抛出，--在activity销毁之后执行了commit();  就说这么多了，欢迎某某来解决^o^
*  @author zgq
*  @version 1.0
*  @date 2018/4/4
*/
public class MainActivity extends MyBaseActivity {

    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.rg_main_tab)
    RadioGroup rg;
    private ArrayList<Fragment> fragments;
    private FragmentControl fragmentControl;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        fragments = new ArrayList<>();
        fragments.add(new FragmentHome());
        fragments.add(new FragmentAct());
        fragments.add(new FragmentVideo());
        fragments.add(new FragmentMe());
        fragmentControl = FragmentControl.getInstance(this, fragments, R.id.fl_content);
        setListener();
        setPage(0);
    }


    /**
     * 设置选中页数
     */
    private void setPage(int page) {
        RadioButton rb = (RadioButton) rg.getChildAt(page);
        rb.setChecked(true);
        fragmentControl.showFragment(page);
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
                        fragmentControl.showFragment(i);
                        break;
                    }
                }
            }
        });

    }
}
