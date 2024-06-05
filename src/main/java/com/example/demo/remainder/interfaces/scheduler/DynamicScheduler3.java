package com.example.demo.remainder.interfaces.scheduler;

import com.example.demo.remainder.util.DynamicTask;
import org.springframework.stereotype.Component;

@Component
@DynamicTask(fixedDelay = 0L, cron = "0 0 * ? * *", name = "test", code = "TASK003", notes = "to execute saved log")
public class DynamicScheduler3 implements Runnable{

    @Override
    public void run() {
// cek ke table log >> panggil class execurtor >> panggil method executenya
//        System.out.println("dynamic fixed task executed");
    }


}
