package com.bean.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class WebMvcConfig {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        ExecutorService es = Executors.newCachedThreadPool();
        //Executors.new

    }
}
