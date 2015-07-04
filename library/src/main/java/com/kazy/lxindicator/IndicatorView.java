package com.kazy.lxindicator;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class IndicatorView extends LinearLayout {

    private LinearLayout rootView;

    private final static int wrapContent = ViewGroup.LayoutParams.WRAP_CONTENT;

    private final static ViewGroup.LayoutParams wrapContentParams = new ViewGroup.LayoutParams(wrapContent, wrapContent);

    public IndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(int pageCount, int resId) {
        removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        rootView = (LinearLayout) inflater.inflate(R.layout.indicator, this, false);
        for (int i = 0; i < pageCount; i++) {
            ImageView indicatorImageView = new ImageView(getContext());
            indicatorImageView.setLayoutParams(wrapContentParams);
            indicatorImageView.setImageDrawable(getDrawableResource(resId));
            rootView.addView(indicatorImageView);
        }
        addView(rootView);
        setPagePosition(0);
    }

    public void init(int[] resIds) {
        removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        rootView = (LinearLayout) inflater.inflate(R.layout.indicator, this, false);
        for (int i = 0; i < resIds.length; i++) {
            ImageView indicatorImageView = new ImageView(getContext());
            indicatorImageView.setLayoutParams(wrapContentParams);
            indicatorImageView.setImageDrawable(getDrawableResource(resIds[i]));
            rootView.addView(indicatorImageView);
        }
        addView(rootView);
    }

    @SuppressWarnings("deprecation")
    public Drawable getDrawableResource(int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getContext().getDrawable(id);
        } else {
            return getResources().getDrawable(id);
        }
    }

    public void setPagePosition(int position) {
        setChecked(position, true);
    }

    public void setChecked(int position, boolean checked) {
        int childCount = rootView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) rootView.getChildAt(i);
            imageView.setSelected(false);
        }

        ImageView imageView = (ImageView) rootView.getChildAt(position);
        imageView.setSelected(checked);
    }
}

