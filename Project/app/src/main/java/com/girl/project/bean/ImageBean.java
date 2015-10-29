package com.girl.project.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/10/23.
 */
public class ImageBean implements Serializable {
    public String getCreated_at() {
        return created_at;
    }


    public void setChannel(String channel) {

        this.channel = channel;
    }

    public void setUrl(String url) {

        this.url = url;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setId(String id) {

        this.id = id;
    }

    private String id;
    private String created_at;
    private String url;


    public String getChannel() {

        return channel;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }

    private String channel;
    private Meta meta=new Meta();
}
