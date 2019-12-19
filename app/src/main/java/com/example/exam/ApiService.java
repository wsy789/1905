package com.example.exam;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    //https://www.wanandroid.com/project/list/1/json?cid=294
    String WAN_URL="https://www.wanandroid.com/project/";
    @GET("list/1/json?cid=294")
    Observable<WanBean> setDate();
}
