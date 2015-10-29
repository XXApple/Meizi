package com.girl.project.bean;

import android.media.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/23.
 */
public class ImageList implements Serializable{
    private List<ImageBean> imageBeanList = new ArrayList<>();
    public List<ImageBean> getLists() {
        return imageBeanList;
    }

    public void setLists(List<ImageBean> lists) {
        this.imageBeanList = lists;
    }

}