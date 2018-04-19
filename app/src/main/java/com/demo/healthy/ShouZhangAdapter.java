package com.demo.healthy;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class ShouZhangAdapter extends RecyclerView.Adapter<ShouZhangAdapter.ViewHolder> {

    private Context context;

    private List<ShouZhang> list;

    public ShouZhangAdapter(Context context, List<ShouZhang> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ShouZhangAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_sz_item, null));
    }

    @Override
    public void onBindViewHolder(ShouZhangAdapter.ViewHolder holder, int position) {
        final ShouZhang shiCai = list.get(position);
        holder.textView.setText(shiCai.getContent());
        holder.textViewT.setText(shiCai.getStime());
        ImageOptions imageOptions = new ImageOptions.Builder().setImageScaleType(ImageView.ScaleType.FIT_XY).setUseMemCache(true).setAutoRotate(true)
                .setFailureDrawable(context.getDrawable(android.R.drawable.ic_menu_report_image)).build();
        x.image().bind(holder.imageView, shiCai.getUrl().split("\\,")[0], imageOptions);
//        holder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onItemClickListener != null) {
//                    onItemClickListener.onItemClick(shiCai);
//                }
//            }
//        });
        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog dia = new AlertDialog.Builder(context).setTitle("提示").setMessage("是否删除该记录?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                list.remove(shiCai);
                                notifyDataSetChanged();
                                onItemClickListener.onItemClick(shiCai);
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create();
                dia.show();

                return false;
            }
        });

    }

    public void add(ShouZhang shouZhang) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(shouZhang);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView textViewT;


        public ViewHolder(View itemView) {
            super(itemView);
            //linearLayout.setMinimumHeight(800);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textView = (TextView) itemView.findViewById(R.id.editText2);
            textViewT = (TextView) itemView.findViewById(R.id.editText);
        }
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(ShouZhang caiPu);
    }
}
