package com.hqs.springboot.shutdowndemo.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author huangqingshi
 * @Date 2019-08-17
 */
@RestController
public class ShutDownController implements ApplicationContextAware {

    private ApplicationContext context;

    @PostMapping("/shutDownContext")
    public String shutDownContext() {
        ConfigurableApplicationContext ctx = (ConfigurableApplicationContext) context;
        ctx.close();
        return "context is shutdown";
    }

    @GetMapping("/")
    public String getIndex() {
        System.out.println("nowtime = "+ new Date());
        return "OK-->"+new Date();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
