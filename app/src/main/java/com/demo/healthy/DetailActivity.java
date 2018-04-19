package com.demo.healthy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.ex.DbException;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.Iterator;
import java.util.List;

/**
 * 点击菜谱详情界面
 */
@ContentView(R.layout.activity_detail)
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        initView();
    }

    @ViewInject(R.id.ll_zl)
    LinearLayout ll_zl;
    @ViewInject(R.id.ll_fl)
    LinearLayout ll_fl;
    @ViewInject(R.id.steps)
    LinearLayout ll_steps;

    /**
     * 初始化控件
     */
    private void initView() {
        CaiPu caiPu = (CaiPu) getIntent().getSerializableExtra("cp");
        if (caiPu == null) return;
        String zl = caiPu.getZl();
        String fl = caiPu.getFl();

        bindData(zl, ll_zl);
        bindData(fl, ll_fl);
        try {
            List<Step> fid = HgcDb.getInstance().getDb().selector(Step.class).where("fid", "=", caiPu.getId()).findAll();
            for (Step step : fid) {
                LinearLayout stepItemView = createStepItemView(step);
                ll_steps.addView(stepItemView);
            }

        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 绑定数据
     * @param json  json 数据
     * @param ll
     */
    private void bindData(String json, LinearLayout ll) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                final String next = keys.next();
                String value = jsonObject.getString(next);
                LinearLayout itemView = createItemView(next, value);
                ll.addView(itemView);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建菜谱步骤
     * @param step
     * @return
     */
    private LinearLayout createStepItemView(Step step) {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setPadding(10,10,10,0);
        ImageView imageView = new ImageView(this);
        //imageView.setMinimumWidth(200);
        //imageView.setMinimumHeight(200);
        imageView.setMaxWidth(10);
        ImageOptions imageOptions = new ImageOptions.Builder().setUseMemCache(true)
                .setImageScaleType(ImageView.ScaleType.FIT_XY).setFailureDrawable(getDrawable(android.R.drawable.ic_menu_report_image)).build();
        x.image().bind(imageView, step.getUrl(), imageOptions);
        TextView textView = new TextView(this);
        textView.setText(step.getContent());
        linearLayout.addView(imageView);
        linearLayout.addView(textView);
        return linearLayout;
    }

    /**
     * 创建Item
     * @param next
     * @param value
     * @return
     */
    private LinearLayout createItemView(String next, String value) {
        LinearLayout linearLayout = new LinearLayout(this);             //线性布局
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);                  //水平方向排列
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);//布局参数
        layoutParams.weight = 1;                                                 //比重1
        TextView textView = new TextView(this);                          //文本
        textView.setText(next);                                                  //设置文本内容
        textView.setGravity(Gravity.LEFT);                                       //居右
        TextView textView1 = new TextView(this);
        textView1.setText(value);
        textView1.setGravity(Gravity.RIGHT);
        linearLayout.addView(textView, layoutParams);
        linearLayout.addView(textView1, layoutParams);
        return linearLayout;
    }
}
