package com.test.bean;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DemoListen implements ApplicationListener<DemoEvent>{
    @Override
    public void onApplicationEvent(DemoEvent event) {
        System.out.println(event.getMsg());
    }
}
