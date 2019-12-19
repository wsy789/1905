package com.example.exam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class WanReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        ArrayList<WanBean.DataBean.DatasBean> list= (ArrayList<WanBean.DataBean.DatasBean>) intent.getSerializableExtra("list");
        int posi = intent.getIntExtra("posi",-1);
        WanBean.DataBean.DatasBean datasBean = list.get(posi);
        Log.i("TAG", "onReceive: "+datasBean.getTitle());
    }

}
