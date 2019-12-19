package com.example.exam;


import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exam.presenter.WanPresenter;
import com.example.exam.view.WanView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements WanView {


    @BindView(R.id.recy_item)
    RecyclerView recyItem;
    private RecyAdapter recyAdapter;
    private ArrayList<WanBean.DataBean.DatasBean> list;
    private WanPresenter wanPresenter;
    int posi;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, null);
        //使用ButterKnife
        ButterKnife.bind(this, view);
        onRecy();
        inDate();
        return view;
    }

    /* //动态注册广播，并传递title字段
     @Override
     public void onResume() {
         super.onResume();
         WanReceiver wanReceiver = new WanReceiver();
         Intent intent = new Intent(getActivity(), WanReceiver.class);
         intent.putExtra("title", list.get(posi).getTitle());
         getActivity().sendBroadcast(intent);

     }*/
    //动态注册广播，并传递title字段
    @Override
    public void onResume() {
        super.onResume();
        WanReceiver wanReceiver = new WanReceiver();
        IntentFilter intentFilter = new IntentFilter("aaa");
        getActivity().registerReceiver(wanReceiver, intentFilter);


    }
//发送广播
    private void sendGuangBo() {
        Intent intent = new Intent("aaa");
        intent.putExtra("posi", posi);
        intent.putExtra("list", list);
        getActivity().sendBroadcast(intent);
    }

    //使用MVP获取网络数据
    private void inDate() {
        wanPresenter = new WanPresenter(this);
        wanPresenter.getDate();
    }

    //使用RecyclerView展示数据
    private void onRecy() {
        list = new ArrayList<>();
        recyItem.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyItem.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        recyAdapter = new RecyAdapter(getActivity(), list);
        recyItem.setAdapter(recyAdapter);

        recyAdapter.setOnClickItemLis(new RecyAdapter.OnClickItemLis() {
            @Override
            public void onClickItem(int position) {
                posi = position;
                sendGuangBo();
            }
        });
    }


    //成功方法
    @Override
    public void toDate(List<WanBean.DataBean.DatasBean> list1) {
        list.addAll(list1);
        recyAdapter.notifyDataSetChanged();
    }

    //失败方法
    @Override
    public void toToast(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }
}
