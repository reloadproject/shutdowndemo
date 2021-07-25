package com.hqs.springboot.shutdowndemo;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ShutdowndemoApplication {

    public static void main(String[] args) {

        System.out.println("Shutdown Demo Application started");
        /* method 1: use curl -X POST http://localhost:3333/actuator/shutdown */
        //SpringApplication.run(ShutdowndemoApplication.class, args);


        /* method 2: use ctx.close to shutdown all application context */
        /*ConfigurableApplicationContext ctx = SpringApplication.run(ShutdowndemoApplication.class, args);
        try {
            System.out.println("10秒后停止服务！！");
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ctx.close();*/

          /* method 3 : generate a pid in a specified path, while use command to shutdown pid :
            'cat /usr/app.pid | xargs kill' */
        //工作中一般用这种
        //SpringApplication application = new SpringApplication(ShutdowndemoApplication.class);
        //application.addListeners(new ApplicationPidFileWriter("/usr/app.pid"));
        //application.addListeners(new ApplicationPidFileWriter()); //pid在配置文件中指定
        //application.run();


        /* method 4: exit this application using static method */
       /* ConfigurableApplicationContext ctx = SpringApplication.run(ShutdowndemoApplication.class, args);
        try {
            System.out.println("10秒后停止服务！！");
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exitApplication(ctx);*/

         /* method 5: using self-defined url in ShutDownController.java to shut down context :
            curl -X POST http://localhost:3333/shutDownContext */
        //SpringApplication application = new SpringApplication(ShutdowndemoApplication.class);
        //application.run();
    }

    public static void exitApplication(ConfigurableApplicationContext context) {
        int exitCode = SpringApplication.exit(context, (ExitCodeGenerator) () -> 0);

        System.exit(exitCode);
    }

}
