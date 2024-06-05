package com.example.demo.config;

import com.example.demo.remainder.application.TaskService;
import com.example.demo.remainder.entity.Task;
import com.example.demo.remainder.util.DynamicTask;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class TasksRegistration implements ApplicationListener<ApplicationReadyEvent> {
	

	private final ApplicationContext appCtx;
	private final ThreadPoolTaskScheduler scheduler;
	private final TaskService taskService;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		try {
		
			log.debug("registering available tasks");
			
			String beanDefinitionNames[] = appCtx.getBeanDefinitionNames();
			List<Task> taskList = taskService.findAllTask();
			for (String bdname : beanDefinitionNames) {
				DynamicTask dynamicTask = AnnotationUtils.findAnnotation(appCtx.getType(bdname), DynamicTask.class);
				boolean registered = false;
				if(null != dynamicTask) {

					Optional<Task> task = Optional.empty();
					if (!taskList.isEmpty())
						task = taskList.stream().filter(d -> d.getCode().equals(dynamicTask.code())).findAny();


					long fixedDelay = dynamicTask.fixedDelay();
					String cronExpr = dynamicTask.cron();

					if (task.isPresent()){
						registered = true;
						fixedDelay = task.get().getFixedDelay();
						cronExpr = task.get().getCron();
					}

//					if (dynamicTask.code())
					
					//scheduling with fixedDelay
//					long fixedDelay = task.isPresent() ? task.get().getFixedDelay() : dynamicTask.fixedDelay();
					if(fixedDelay > 0) {
						scheduler.scheduleWithFixedDelay((Runnable) appCtx.getBean(bdname), fixedDelay);
						//10000
					} else if(CronExpression.isValidExpression(cronExpr)) {
						// scheduling with cron
//					String cronExpr = task.isPresent() ? task.get().getCron() : dynamicTask.cron();
						scheduler.schedule((Runnable) appCtx.getBean(bdname), new CronTrigger(cronExpr));
						//0 0 1 ? * *

						//0 10 2 ? * *
					}

					if(!registered) {
						taskService.creteNewTask(dynamicTask);
						log.error("task not registered : " + bdname);
					}
					
				}
				
			}
			log.debug("all tasks successfully registered");
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			System.exit(-1);
		}
		
	}

}
