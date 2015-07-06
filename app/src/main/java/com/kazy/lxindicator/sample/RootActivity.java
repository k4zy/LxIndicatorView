package com.kazy.lxindicator.sample;

import com.kazy.lxindicator.LxIndicatorGroup;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

public class RootActivity extends AppCompatActivity {

    private final static int[] COLOR_LIST = {0xff7cd5aa, 0xfff1e6a2, 0xfffecc5a, 0xffff8b58, 0xffe92440};

    private final static int VIEW_COUNT = COLOR_LIST.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        final LxIndicatorGroup indicatorGroup = (LxIndicatorGroup) findViewById(R.id.indicator_view);
        indicatorGroup.setup(VIEW_COUNT, R.drawable.indicator);
        SampleViewPagerAdapter adapter = new SampleViewPagerAdapter();
        for (int color : COLOR_LIST) {
            FrameLayout view = new FrameLayout(this);
            view.setBackgroundColor(color);
            adapter.addView(view);
        }
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(indicatorGroup.getSyncPositionListener());
    }

}
