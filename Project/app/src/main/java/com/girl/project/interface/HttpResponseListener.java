package com.girl.project;

import android.os.Bundle;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2015/10/26.
 */
public interface HttpResponseListener {
    public void requestObjectSuccess(Object object, Bundle bundle);

    // 请求网络成功(Arrary)
    public void requestArrarySuccess(List<Object> list, Bundle resultCode);
    // 请求网络失败
    public void requestFailure(int statusCode, Header[] headers,
                               String responseString, Throwable throwable);

}
