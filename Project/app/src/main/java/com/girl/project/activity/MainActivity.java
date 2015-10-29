package com.girl.project.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Service;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.girl.project.R;
import com.girl.project.base.BaseActivity;
import com.girl.project.bean.ImageBean;
import com.girl.project.bean.ImageList;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.List;

public class MainActivity extends BaseActivity implements SensorEventListener {
    private ImageView imageview;
    private ImageLoader mImageLoader;
    private DisplayImageOptions options;
    private int imagesize = 0;
    private List<ImageBean> imageBeanList;
    //定义sensor管理器
    private SensorManager mSensorManager;
    //震动
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayout());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().setDisplayShowTitleEnabled(false);
        initView();
    }

    @Override
    protected int getlayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ImageList imageList = (ImageList) getIntent().getSerializableExtra("data");
        imageBeanList = imageList.getLists();
        imageview = (ImageView) findViewById(R.id.main_image);
        mImageLoader = ImageLoader.getInstance();
        this.options = new DisplayImageOptions.Builder()
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT).cacheOnDisc(true)
                .build();
        //获取传感器管理服务
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //震动
        vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        loadImage(imageBeanList);
    }

    /**
     * 显示图片
     * @param imageBeans
     */
    private void loadImage(List<ImageBean> imageBeans) {
        if (imageBeans == null || (imageBeans != null && imageBeans.size() <= 0)) return;
        mImageLoader.displayImage(imageBeans.get(imagesize).getUrl(), imageview, options);
    }



    @Override
    protected void onResume() {
        super.onResume();

        //加速度传感器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                //还有SENSOR_DELAY_UI、SENSOR_DELAY_FASTEST、SENSOR_DELAY_GAME等，
                //根据不同应用，需要的反应速率不同，具体根据实际情况设定
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        mSensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub
        int sensorType = event.sensor.getType();

        //values[0]:X轴，values[1]：Y轴，values[2]：Z轴
        float[] values = event.values;

        if (sensorType == Sensor.TYPE_ACCELEROMETER) {

  /*因为一般正常情况下，任意轴数值最大就在9.8~10之间，只有在你突然摇动手机
  *的时候，瞬时加速度才会突然增大或减少。
  *所以，经过实际测试，只需监听任一轴的加速度大于14的时候，改变你需要的设置
  *就OK了~~~
  */
            if ((Math.abs(values[0]) > 14 || Math.abs(values[1]) > 14 || Math.abs(values[2]) > 14)) {

                vibrator.vibrate(800);
                loadImage(imageBeanList);
                imagesize += 1;


            }
        }

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
