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

import java.util.ArrayList;
import java.util.List;


/**
 * 图片适配器
 * 用来展示图片的
 */
public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.ViewHolder> {

    private Context context;

    private List<String> list;

    public MediaAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MediaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout linearLayout = new LinearLayout(context);
        return new ViewHolder(linearLayout);
    }

    @Override
    public void onBindViewHolder(MediaAdapter.ViewHolder holder, int position) {
        final String string = list.get(position);
        ImageOptions imageOptions = new ImageOptions.Builder().setImageScaleType(ImageView.ScaleType.FIT_XY).setUseMemCache(true).setAutoRotate(true)
                .setFailureDrawable(context.getDrawable(android.R.drawable.ic_menu_report_image)).build();
        x.image().bind(holder.imageView, string, imageOptions);

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

    public void add(String url) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(url);
        notifyDataSetChanged();
    }

    public String toMediaString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : list) {
            stringBuilder.append(string);
            stringBuilder.append(",");
        }
        return stringBuilder.toString();
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(CaiPu caiPu);
    }
}
