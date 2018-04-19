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
 * 来展示春夏秋冬和热门
 */
public class Season2View extends LinearLayout implements View.OnClickListener {

    private TextView spr, sum, aut, win;

    private TextView[] btns;


    public Season2View(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.weight = 1;

        spr = new TextView(context);
        spr.setText("春季");
        spr.setTextColor(Color.RED);
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
        // addView(heat, layoutParams);
        addView(spr, layoutParams);
        addView(sum, layoutParams);
        addView(aut, layoutParams);
        addView(win, layoutParams);
        //  heat.setOnClickListener(this);
        spr.setOnClickListener(this);
        sum.setOnClickListener(this);
        aut.setOnClickListener(this);
        win.setOnClickListener(this);
        btns = new TextView[4];
        btns[0] = spr;
        btns[1] = sum;
        btns[2] = aut;
        btns[3] = win;
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
