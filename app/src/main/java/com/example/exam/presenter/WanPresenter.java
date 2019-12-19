package com.example.exam.presenter;

import com.example.exam.HomeFragment;
import com.example.exam.WanBean;
import com.example.exam.model.WanModel;
import com.example.exam.view.WanView;

import java.util.List;

public class WanPresenter {
    private WanView wanView;
    private final WanModel wanModel;

    public WanPresenter(WanView wanView) {

        this.wanView = wanView;
        wanModel = new WanModel();
    }

    public void getDate() {
        wanModel.getDate(new WanCallBack() {
            @Override
            public void onSuccess(List<WanBean.DataBean.DatasBean> list1) {
                if (list1 != null && wanView != null) {
                    wanView.toDate(list1);
                }
            }

            @Override
            public void onFail(String str) {
                wanView.toToast(str);
            }
        });
    }
}
