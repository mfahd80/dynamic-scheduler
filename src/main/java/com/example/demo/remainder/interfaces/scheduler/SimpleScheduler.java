package com.example.demo.remainder.interfaces.scheduler;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SimpleScheduler{

//    @Scheduled(cron = "0 * * * * *")
//    private void executeCron(){
//        System.out.println("simple cron task executed");
//    }
//
    @Scheduled(fixedRate = 10000, initialDelay = 5000)
    private void executeFixedDelay(){
        System.out.println("simple fixed delay task executed");
    }

//    @Scheduled(cron = "${scheduler.executor.cron}")
//    private void executeCron(){
//        System.out.println("simple cron task executed");
//    }

//    @Scheduled(fixedDelayString = "${scheduler.executor.fixed-delay}")
//    private void executeFixedDelay(){
//        System.out.println("simple fixed delay task executed");
//    }
}
