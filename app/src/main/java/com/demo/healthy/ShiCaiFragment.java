package com.demo.healthy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;


/**
 * 食材界面
 */
@ContentView(R.layout.fragment_shicai)
public class ShiCaiFragment extends Fragment {

    private boolean isInject = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!isInject) {
            x.view().inject(this, getView());
        }

        seasonView.setOnSeasonClickListener(new SeasonView.OnSeasonClickListener() {
            @Override
            public void onSeasonClick(View v, int index) {
                System.out.println("index = " + index);
                type = index;
                bindData();
            }
        });

        initView();


    }

    private int type = 0;

    @ViewInject(R.id.rv_sc)
    RecyclerView recyclerView;


    private void initView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        bindData();

    }

    private void bindData() {
        try {
            List<ShiCai> all = HgcDb.getInstance().getDb().selector(ShiCai.class).where("type", "=", type).findAll();
            ShiCaiAdapter adapter = new ShiCaiAdapter(getActivity(), all);
            recyclerView.setAdapter(adapter);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isInject = true;
        return x.view().inject(this, inflater, container);
    }

    @ViewInject(R.id.season)
    private SeasonView seasonView;


}
