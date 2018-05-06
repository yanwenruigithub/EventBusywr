package com.ruifeng.evenbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ruifeng.evenbus.event.EventMsg;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG="MainActivity";
    private Button mainMsg,main_sticky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注册订阅者
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
      mainMsg= findViewById(R.id.mainEvent);
      main_sticky=findViewById(R.id.main_sticky);
      mainMsg.setOnClickListener(this);
      main_sticky.setOnClickListener(this);

    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onPostiongMsg(EventMsg msg){
        Log.e(TAG,"onPostiongMsg:"+msg.getEventMsg());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainMsg(EventMsg msg){
        Log.e(TAG,"onMainMsg:"+msg.getEventMsg());

    }
    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void onMain_orderedMsg(EventMsg msg){
        Log.e(TAG,"onMain_orderedMsg:"+msg.getEventMsg());

    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onBackgroundMsg(EventMsg msg){
        Log.e(TAG,"onBackgroundMsg:"+msg.getEventMsg());
    }
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onAsyncMsg(EventMsg msg){
        Log.e(TAG,"onAsyncMsg:"+msg.getEventMsg());
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销订阅
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.mainEvent){
            SecondActivity.startActivity(MainActivity.this);
        }else if (v.getId()==R.id.main_sticky){
            EventBus.getDefault().postSticky(new EventMsg("hello staticEvent"));
        }
    }
}
