package com.ruifeng.evenbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ruifeng.evenbus.event.EventMsg;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2018/4/30/030.
 */

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG="SecondActivity";
    private Button secondevent;
    public static void startActivity(Context context){
        Intent intent=new Intent(context,SecondActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        EventBus.getDefault().register(this);
    }

    private void initView() {
       secondevent= findViewById(R.id.secondEvent);
       secondevent.setOnClickListener(this);

    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void enStickyEvent(EventMsg msg){
        Log.e(TAG,"enStickyEvent:"+msg.getEventMsg());
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.secondEvent){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    EventBus.getDefault().post(new EventMsg("hello eventBus"));
                }
            }).start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
