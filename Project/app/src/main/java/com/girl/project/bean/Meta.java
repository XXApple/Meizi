package com.girl.project.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/10/26.
 */
public class Meta implements Serializable {
    private String type;
    private String width;
    private String height;

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {

        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
