package com.girl.project.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

/**
 * Created by Administrator on 2015/10/27.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflater = getLayoutInflater();
    }

    protected abstract int getlayout();

    protected abstract void initView();
}
