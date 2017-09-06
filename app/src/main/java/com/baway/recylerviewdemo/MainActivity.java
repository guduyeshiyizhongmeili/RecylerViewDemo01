package com.baway.recylerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRv;
    private List<String> list = new ArrayList<>();
    /**
     * 添加
     */
    private Button mBtAdd;
    /**
     * 删除
     */
    private Button mBtDel;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.rv);
        //设置布局加载器
        mRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        mRv.setLayoutManager(new GridLayoutManager(this, 3));
//        mRv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        //加分割线
//        mRv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRv.addItemDecoration(new DividerGridItemDecoration(this));
        //添加动画
        mRv.setItemAnimator(new DefaultItemAnimator());

        //设置适配器
        for (int i = 0; i < 50; i++) {
            list.add("item" + i);
        }


        adapter = new MyAdapter(this, list);
        mRv.setAdapter(adapter);
        mBtAdd = (Button) findViewById(R.id.bt_add);
        mBtAdd.setOnClickListener(this);
        mBtDel = (Button) findViewById(R.id.bt_del);
        mBtDel.setOnClickListener(this);

        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this,"点击",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(int position) {
                Toast.makeText(MainActivity.this,"长按",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add:
                adapter.addData(1);
                break;
            case R.id.bt_del:
                adapter.removeData(1);
                break;
        }
    }
}
