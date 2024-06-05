package com.example.demo.remainder.application;

import com.example.demo.remainder.entity.Task;
import com.example.demo.remainder.interfaces.dto.CreateTaskDto;
import com.example.demo.remainder.interfaces.repository.TaskRepository;
import com.example.demo.remainder.util.DynamicTask;
import lombok.AllArgsConstructor;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final Scheduler scheduler;

    public List<Task> findAllTask(){
        return taskRepository.findAll();
    }

    public void creteNewTask(DynamicTask dynamicTask){
        Task task = new Task(
                null,
                dynamicTask.code(),
                "active",
                dynamicTask.name(),
                dynamicTask.fixedDelay(),
                dynamicTask.cron(),
                dynamicTask.notes(),
                0L
        );

        taskRepository.save(task);
    }

    public void createQuartzTask(CreateTaskDto request){
        try {
            scheduler.scheduleJob(generateJobDetail(request), generateTrigger(request));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private JobDetail generateJobDetail(CreateTaskDto request){
        return JobBuilder.newJob(TaskJob.class)
                .withDescription(request.getJobName())
                .build();
    }

    private Trigger generateTrigger(CreateTaskDto request){
        return TriggerBuilder.newTrigger()
                .withDescription(request.getJobName() + " - Trigger")
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(request.getDelaySecond())
                )
//                .startAt(Date.from(Instant.now()))
                .build();
    }
}
