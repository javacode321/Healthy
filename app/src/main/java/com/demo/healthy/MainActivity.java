package com.demo.healthy;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.*;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import org.xutils.ex.DbException;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * 主界面
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {


    Fragment[] fragments;
    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        fragments = new Fragment[3];
        fragments[0] = new ShiCaiFragment();
        fragments[1] = new CaiPuFragment();
        fragments[2] = new ShouZhangFragment();
        tv_sc.setTextColor(Color.RED);
        getSupportFragmentManager().beginTransaction().add(R.id.content, fragments[0]).commitAllowingStateLoss();

//        CaiPu caiPu = new CaiPu();
//        caiPu.setJianjie("(迷迭香)在鲜艳的色彩里感受春天的生命力。");
//        caiPu.setTimeAndfl("10,10,3-4");
//        caiPu.setType(0);
//        caiPu.setName("香椿拌豆腐");
//        caiPu.setUrl("http://recipe0.hoto.cn/pic/recipe/l/70/2c/1059952_b39e47.jpg");
//        caiPu.setZl("{\"香椿\":\"100g\",\"鸡蛋\":\"2个\"}");
//        caiPu.setFl("{\"油\":\"1勺\",\"盐\":\"1勺\",\"料酒\":\"1勺\"}");
//        Step step = new Step();
//        step.setContent("1.豆腐切块。");
//        step.setUrl("http://recipe1.hoto.cn/pic/step/l/cb/62/4809419.jpg");
//        step.setFid(1);

//        try {
//            HgcDb.getInstance().getDb().save(step);
//        } catch (DbException e) {
//            e.printStackTrace();
//        }

        AlarmManagerUtil.setAlarm(this, 0, 6, 30, 0, 0, "闹钟响了", 2);
        AlarmManagerUtil.setAlarm(this, 0, 8, 30, 0, 0, "闹钟响了", 2);
        AlarmManagerUtil.setAlarm(this, 0, 11, 00, 0, 0, "闹钟响了", 2);
        AlarmManagerUtil.setAlarm(this, 0, 12, 50, 0, 0, "闹钟响了", 2);
        AlarmManagerUtil.setAlarm(this, 0, 15, 00, 0, 0, "闹钟响了", 2);
        AlarmManagerUtil.setAlarm(this, 0, 17, 30, 0, 0, "闹钟响了", 2);
        AlarmManagerUtil.setAlarm(this, 0, 22, 00, 0, 0, "闹钟响了", 2);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AlarmManagerUtil.ALARM_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);

    }


    @ViewInject(R.id.iv_sc)
    ImageView iv_sc;
    @ViewInject(R.id.iv_cp)
    ImageView iv_cp;
    @ViewInject(R.id.iv_sz)
    ImageView iv_sz;

    @ViewInject(R.id.tv_sc)
    TextView tv_sc;
    @ViewInject(R.id.tv_cp)
    TextView tv_cp;
    @ViewInject(R.id.tv_sz)
    TextView tv_sz;


    public void tab(View v) {
        switch (v.getId()) {
            case R.id.rl_sc:
                tv_sc.setTextColor(Color.RED);
                tv_cp.setTextColor(Color.BLACK);
                tv_sz.setTextColor(Color.BLACK);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, fragments[0]).commitAllowingStateLoss();
                break;
            case R.id.rl_cp:
                tv_sc.setTextColor(Color.BLACK);
                tv_cp.setTextColor(Color.RED);
                tv_sz.setTextColor(Color.BLACK);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, fragments[1]).commitAllowingStateLoss();
                break;
            case R.id.rl_sz:
                tv_sc.setTextColor(Color.BLACK);
                tv_cp.setTextColor(Color.BLACK);
                tv_sz.setTextColor(Color.RED);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, fragments[2]).commitAllowingStateLoss();
                break;
        }

    }


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (!intent.getAction().equals(AlarmManagerUtil.ALARM_ACTION)) return;
            //显示图片 和 震动
            final Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(new long[]{1000, 1000}, 0);
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setMinimumHeight(500);
            ImageOptions imageOptions = new ImageOptions.Builder().setUseMemCache(true).setImageScaleType(ImageView.ScaleType.FIT_XY).setFailureDrawable(getDrawable(R.drawable.dialog_drinking_bg)).build();
            x.image().bind(imageView, "", imageOptions);
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).setView(imageView).setCancelable(false).setPositiveButton("关闭提醒", new DialogInterface.OnClickListener() {


                @Override
                public void onClick(DialogInterface dialog, int which) {
                    vibrator.cancel();
                }
            }).create();
            dialog.show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}
