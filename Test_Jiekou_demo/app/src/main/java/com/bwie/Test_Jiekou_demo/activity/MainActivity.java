package com.bwie.Test_Jiekou_demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.bwie.Test_Jiekou_demo.R;
import com.bwie.Test_Jiekou_demo.net.RetrofitUtils;
import com.bwie.Test_Jiekou_demo.api.Api;
import com.bwie.Test_Jiekou_demo.apiserver.ApiServer;
import com.bwie.Test_Jiekou_demo.bean.Bean;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private String s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实现时间戳
        long nowtime0 = new Date().getTime();
        long nowtime = nowtime0 / 1000;
        String time = String.valueOf(nowtime);
        Log.e("xxx",String.valueOf(nowtime));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("312045ED9D036BEED16E96F3878E222ED7E58AC9");
        stringBuffer.append("ANDROID");
        stringBuffer.append("12345");
        stringBuffer.append("1");
        stringBuffer.append(nowtime+"");
        String s = stringBuffer.toString();
        Log.i("xxx",s);
        //MD5加密
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            //字符串转换成数组
            byte[] result = digest.digest(s.getBytes());
            //消息摘要的结果一般都是转换成16 进制字符串形式展示
            String s2 = new BigInteger(1, result).toString(16);
            s1 = s2.toUpperCase();
            Log.i("xxx", s1);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        ApiServer apiService = RetrofitUtils.getInstance().getApiService(Api.path, ApiServer.class);
        Observable<Bean> observable = apiService.getData("ANDROID", "12345", "1", time, s1);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("zzz",e+"");
                    }

                    @Override
                    public void onNext(Bean bean) {
                        Bean.DataBean dataBean = bean.data;
                        String privateKey = dataBean.private_key;
                        Log.i("xxx",privateKey);
                        Toast.makeText(MainActivity.this,privateKey+"LLLLLLLL",Toast.LENGTH_SHORT).show();
                    }
                });


    }
}
