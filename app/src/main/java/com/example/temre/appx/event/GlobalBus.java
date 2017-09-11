package com.example.temre.appx.event;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by temre on 9.08.2017.
 */

public class GlobalBus {

    private static EventBus eventBus;

    public static EventBus getBus(){

        if(eventBus ==null)
        {
            eventBus = EventBus.getDefault();
        }
        return eventBus;


    }

}
