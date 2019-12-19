package com.example.exam.model;

import com.example.exam.ApiService;
import com.example.exam.WanBean;
import com.example.exam.presenter.WanCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WanModel {
    public void getDate(WanCallBack wanCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.WAN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<WanBean> beanObservable = apiService.setDate();
        beanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WanBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WanBean wanBean) {
                        wanCallBack.onSuccess(wanBean.getData().getDatas());
                    }

                    @Override
                    public void onError(Throwable e) {
                        wanCallBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
