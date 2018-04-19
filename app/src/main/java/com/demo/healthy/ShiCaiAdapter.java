package com.demo.healthy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * 食材适配器
 *
 * 显示食材
 */
public class ShiCaiAdapter extends RecyclerView.Adapter<ShiCaiAdapter.ViewHolder> {

    private Context context;

    private List<ShiCai> list;

    public ShiCaiAdapter(Context context, List<ShiCai> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ShiCaiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout linearLayout = new LinearLayout(context);
        return new ViewHolder(linearLayout);
    }

    @Override
    public void onBindViewHolder(ShiCaiAdapter.ViewHolder holder, int position) {
        ShiCai shiCai = list.get(position);
        holder.textView.setText(shiCai.getName());
        ImageOptions imageOptions = new ImageOptions.Builder().setImageScaleType(ImageView.ScaleType.FIT_XY).setUseMemCache(true).setAutoRotate(true)
                .setFailureDrawable(context.getDrawable(android.R.drawable.ic_menu_report_image)).build();
        x.image().bind(holder.imageView, shiCai.getUrl(), imageOptions);

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            LinearLayout linearLayout = (LinearLayout) itemView;
            //linearLayout.setMinimumHeight(800);
           // linearLayout.setMinimumWidth(200);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(450, 450);
            params.gravity = Gravity.CENTER;
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            imageView = new ImageView(context);
            textView = new TextView(context);
            textView.setGravity(Gravity.CENTER);
            linearLayout.addView(imageView, params);
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(-2, -2);
            params1.gravity = Gravity.CENTER;
            linearLayout.addView(textView, params1);
        }
    }
}
