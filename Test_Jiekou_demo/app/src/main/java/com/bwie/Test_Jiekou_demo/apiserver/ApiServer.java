package com.bwie.Test_Jiekou_demo.apiserver;

import com.bwie.Test_Jiekou_demo.bean.Bean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by C-PC on 2017/12/4.
 */

public interface ApiServer {
    /**
     * https://pretty.f8cam.com:17211/app/v1/first_hand?
     * type=ANDROID&dev_id=12345&ver_code=1&tick=1512390819452&sign=127195354044261851032488680759573465646
     * @return
     */
    @POST("app/v1/first_hand")
    @FormUrlEncoded
    Observable<Bean> getData(@Field("type") String type, @Field("dev_id") String dev_id, @Field("ver_code") String ver_code, @Field("tick") String tick, @Field("sign") String sign);
}
