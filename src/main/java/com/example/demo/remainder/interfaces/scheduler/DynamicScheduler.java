package com.example.demo.remainder.interfaces.scheduler;

import com.example.demo.remainder.util.DynamicTask;
import org.springframework.stereotype.Component;

@Component
@DynamicTask(fixedDelay = 5000, cron = "", name = "test", code = "TASK001", notes = "to execute fixed")
public class DynamicScheduler implements Runnable{

    @Override
    public void run() {

//        System.out.println("dynamic fixed task executed");
    }


}
