package com.common.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.common.R;
import com.common.adapter.DividerItemDecoration;
import com.common.adapter.HomeAdapter;

import java.util.List;

/**
 * Created by gao on 2017/9/27.
 */

public abstract class RecycleActivity extends AppCompatActivity {
    protected RecyclerView mRecyclerView;
    protected List<String> mDatas;
    protected HomeAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
        mAdapter = new HomeAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        initData();
        mAdapter.setOnItemClickLitener(new HomeAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                handleClick(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(RecycleActivity.this, position + " long click",
                        Toast.LENGTH_SHORT).show();
                mAdapter.removeData(position);
            }
        });
    }

    abstract protected void handleClick(int position);

    abstract public void initData();
}
