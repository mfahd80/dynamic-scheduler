package com.example.demo.remainder.interfaces.scheduler;

import com.example.demo.remainder.util.DynamicTask;
import org.springframework.stereotype.Component;

@Component
@DynamicTask(fixedDelay = 0, cron = "* * * ? * *", name = "test", code = "TASK002", notes = "to execute cron")
public class Dynamic2Scheduler implements Runnable{

    @Override
    public void run() {
//        System.out.println("dynamic cron task executed");
    }


}
