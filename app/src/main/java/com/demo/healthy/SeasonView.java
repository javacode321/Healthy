package com.demo.healthy;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 自定义控件
 */
public class SeasonView extends LinearLayout implements View.OnClickListener {

    private TextView spr, sum, aut, win, heat;

    private TextView[] btns;


    public SeasonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.weight = 1;
        heat = new TextView(context);
        heat.setText("热门");
        heat.setTextColor(Color.RED);
        heat.setTextSize(20);
        heat.setGravity(Gravity.CENTER);
        spr = new TextView(context);
        spr.setText("春季");
        spr.setTextColor(Color.BLACK);
        spr.setTextSize(20);
        spr.setGravity(Gravity.CENTER);
        sum = new TextView(context);
        sum.setText("夏季");
        sum.setTextColor(Color.BLACK);
        sum.setTextSize(20);
        sum.setGravity(Gravity.CENTER);
        aut = new TextView(context);
        aut.setText("秋季");
        aut.setTextColor(Color.BLACK);
        aut.setTextSize(20);
        aut.setGravity(Gravity.CENTER);
        win = new TextView(context);
        win.setText("冬季");
        win.setTextColor(Color.BLACK);
        win.setTextSize(20);
        win.setGravity(Gravity.CENTER);
        addView(heat, layoutParams);
        addView(spr, layoutParams);
        addView(sum, layoutParams);
        addView(aut, layoutParams);
        addView(win, layoutParams);
        heat.setOnClickListener(this);
        spr.setOnClickListener(this);
        sum.setOnClickListener(this);
        aut.setOnClickListener(this);
        win.setOnClickListener(this);
        btns = new TextView[5];
        btns[0] = heat;
        btns[1] = spr;
        btns[2] = sum;
        btns[3] = aut;
        btns[4] = win;
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < btns.length; i++) {
            TextView tv = btns[i];
            if (v == tv) {
                tv.setTextColor(Color.RED);
                if (onSeasonClickListener != null) {
                    onSeasonClickListener.onSeasonClick(v, i);
                }
            } else {
                tv.setTextColor(Color.BLACK);
            }
        }


    }

    OnSeasonClickListener onSeasonClickListener;

    public void setOnSeasonClickListener(OnSeasonClickListener onSeasonClickListener) {
        this.onSeasonClickListener = onSeasonClickListener;
    }

    public interface OnSeasonClickListener {
        void onSeasonClick(View v, int index);
    }

}
