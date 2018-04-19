package com.demo.healthy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;


/**
 * 菜谱 fragment 界面
 */

@ContentView(R.layout.fragment_caipu)
public class CaiPuFragment extends Fragment implements CaiPuAdapter.OnItemClickListener {

    /**
     * 是否初始化过
     */
    private boolean isInject = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isInject = true;
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (!isInject) {
            x.view().inject(this, getView());
        }
        super.onViewCreated(view, savedInstanceState);

        intiView();

    }

    @ViewInject(R.id.rv_cp)
    RecyclerView recyclerView;

    @ViewInject(R.id.season)
    Season2View season2View;

    /**
     * 初始化控件
     */
    private void intiView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        season2View.setOnSeasonClickListener(new Season2View.OnSeasonClickListener() {
            @Override
            public void onSeasonClick(View v, int index) {
                type = index;
                bindData();
            }
        });
        bindData();
    }

    private int type;

    /**
     * 绑定数据
     */
    private void bindData() {
        try {
            List<CaiPu> list = HgcDb.getInstance().getDb().selector(CaiPu.class).where("type", "=", type).findAll();
            CaiPuAdapter adapter = new CaiPuAdapter(getActivity(), list);
            adapter.setOnItemClickListener(this);
            recyclerView.setAdapter(adapter);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onItemClick(CaiPu caiPu) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("cp", caiPu);
        startActivity(intent);
    }
}
