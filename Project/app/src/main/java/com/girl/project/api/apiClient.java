package com.girl.project.api;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;

import com.girl.project.HttpResponseListener;
import com.girl.project.bean.ImageBean;
import com.girl.project.bean.ImageList;
import com.girl.project.bean.Meta;
import com.girl.project.bean.Urls;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2015/10/26.
 */
public class apiClient {
    private String Tag;
    private Gson gson;

    public apiClient() {
        Tag = apiClient.class.getName();
    }



    private HttpResponseListener httpListener;
    private Activity activity;
    public apiClient(HttpResponseListener httpResponseListener,
                     Activity activity) {
        this.httpListener = httpResponseListener;
        this.activity = activity;
         gson=new Gson();

    }
    public void getData(final int tag){
      HttpRequest.get(Urls.data,null,new JsonHttpResponseHandler(){
          @Override
          public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
              super.onSuccess(statusCode, headers, response);
              ImageList imageList=new ImageList();
              List<ImageBean> imageBeans=new ArrayList<ImageBean>();
              try {
                  for (int i = 0; i < response.length(); i++) {
                      JSONObject obj = (JSONObject) response.get(i);
                      ImageBean imageBean=new ImageBean();
                      if(obj.has("id")){
                          imageBean.setId(obj.getString("id"));
                      }
                      if(obj.has("created_at")){
                          imageBean.setCreated_at(obj.getString("created_at"));
                      }
                      if(obj.has("url")){
                          imageBean.setUrl(obj.getString("url"));
                      }
                      if(obj.has("channel")){
                          imageBean.setChannel(obj.getString("channel"));
                      }
                     if(obj.has("meta")){
                         Meta meta=new Meta();
                         JSONObject obj2=(JSONObject)(obj.get("meta"));
                         if(obj2.has("type")){
                             meta.setType(obj2.getString("type"));
                         }
                         if(obj2.has("width")){
                             meta.setWidth(obj2.getString("width"));
                         }
                         if(obj2.has("height")){
                             meta.setHeight(obj2.getString("height"));
                         }
                         imageBean.setMeta(meta);
                     }
                      imageBeans.add(imageBean);
                  }
                  imageList.setLists(imageBeans);
              }catch(Exception e){
                  e.printStackTrace();
              }
                              Bundle bundle = new Bundle();
                bundle.putInt("tag", tag);
                httpListener.requestObjectSuccess(imageList,
                        bundle);
          }

          @Override
          public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
              super.onSuccess(statusCode, headers, response);

          }

          @Override
          public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
              super.onFailure(statusCode, headers, throwable, errorResponse);
          }
      });
    }
}
