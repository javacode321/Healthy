package com.demo.healthy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * 手账本界面
 */
@ContentView(R.layout.fragment_shouzhang)
public class ShouZhangFragment extends Fragment {

    private boolean isInject = false;

    private ShouZhangAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isInject = true;
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!isInject) {
            x.view().inject(this, getView());
        }
        initView();
    }

    @Event(R.id.btn_sz)
    private void sz(View v) {
        Intent intent = new Intent(getActivity(), MediaActivity.class);
        startActivityForResult(intent, 0);
    }

    @ViewInject(R.id.rv_sz)
    RecyclerView recyclerView;

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        try {
            List<ShouZhang> all = HgcDb.getInstance().getDb().selector(ShouZhang.class).findAll();
            adapter = new ShouZhangAdapter(getActivity(), all);
            adapter.setOnItemClickListener(new ShouZhangAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(ShouZhang caiPu) {
                    try {
                        HgcDb.getInstance().getDb().delete(caiPu);
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }
            });
            recyclerView.setAdapter(adapter);
        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        if (resultCode == 0) {
            ShouZhang shouZhang = (ShouZhang) data.getSerializableExtra("sz");
            adapter.add(shouZhang);
        }
    }
}
