package com.bean.config;

import com.test.bean.TestBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CyclicBarrier;

@Configuration
@ComponentScan("com.test")
public class SpringConfig {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        TestBean tb = context.getBean(TestBean.class);
        TestBean tb2 = context.getBean(TestBean.class);
        tb.setA(1);
        System.out.println(tb == tb2);
        System.out.println(tb.getA());
        CyclicBarrier cb = new CyclicBarrier(10);
        cb.reset();
    }
}


