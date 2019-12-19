package com.example.exam.presenter;

import com.example.exam.WanBean;

import java.util.List;

public interface WanCallBack {
    void onSuccess(List<WanBean.DataBean.DatasBean> list1);
    void onFail(String str);

}
