package com.kazy.lxindicator;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

public class LxIndicatorGroup extends RadioGroup {

    private final static int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;

    private final static int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;

    private final static ViewGroup.LayoutParams WRAP_PARAMS = new ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);

    private final static ViewGroup.LayoutParams MATCH_PARAMS = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);

    private final ViewPager.OnPageChangeListener syncPositionListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            // do nothing
        }

        @Override
        public void onPageSelected(int position) {
            setPagePosition(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            // do nothing
        }
    };

    public LxIndicatorGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);
        setLayoutParams(MATCH_PARAMS);
        setGravity(Gravity.CENTER);
    }

    public ViewPager.OnPageChangeListener getSyncPositionListener() {
        return this.syncPositionListener;
    }

    public void setup(int pageCount, @DrawableRes int resId) {
        removeAllViews();
        for (int i = 0; i < pageCount; i++) {
            ImageView indicatorView = new ImageView(getContext());
            indicatorView.setLayoutParams(WRAP_PARAMS);
            indicatorView.setImageDrawable(getDrawableResource(resId));
            addView(indicatorView);
        }
        setPagePosition(0);
    }

    public void setPagePosition(int position) {
        resetSelectedState();
        getChildAt(position).setSelected(true);
    }

    private void resetSelectedState() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).setSelected(false);
        }
    }

    @SuppressWarnings("deprecation")
    private Drawable getDrawableResource(int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getContext().getDrawable(id);
        } else {
            return getResources().getDrawable(id);
        }
    }
}
