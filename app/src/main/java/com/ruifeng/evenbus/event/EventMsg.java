package com.ruifeng.evenbus.event;

/**
 * Created by Administrator on 2018/4/30/030.
 */

public class EventMsg{

    private  String eventMsg=null;
    public EventMsg(String eventMsg){
        this.eventMsg=eventMsg;
    }

    public String getEventMsg() {
        return eventMsg;
    }

    public void setEventMsg(String eventMsg) {
        this.eventMsg = eventMsg;
    }
}
