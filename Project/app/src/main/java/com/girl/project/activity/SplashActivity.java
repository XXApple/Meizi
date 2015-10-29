package com.girl.project.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.girl.project.HttpResponseListener;
import com.girl.project.R;
import com.girl.project.api.apiClient;
import com.girl.project.bean.ImageList;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2015/10/23.
 */
public class SplashActivity extends Activity implements HttpResponseListener {
    private apiClient mApiclient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        mApiclient=new apiClient(this,this);
        mApiclient.getData(1);
    }


    @Override
    public void requestObjectSuccess(Object object, Bundle bundle) {
        Intent intent=new Intent(SplashActivity.this,MainActivity.class);
        ImageList imageList=(ImageList)object;
        Bundle b=new Bundle();
     b.putSerializable("data", imageList);
        intent.putExtras(b);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void requestArrarySuccess(List<Object> list, Bundle resultCode) {

    }

    @Override
    public void requestFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

    }
}
