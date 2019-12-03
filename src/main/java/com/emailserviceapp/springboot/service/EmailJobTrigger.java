package com.emailserviceapp.springboot.service;

import java.io.File;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailJobTrigger {
	public void triggerEmailService(String userName, String password, File csvFile) {
		System.out.println("Inside EmailJobTrigger Class....!!!");
		try {
			// Create job Object
			JobDetail job = new JobDetail();
			job.setName("EmailJob");
			job.setJobClass(EmailJob.class);

			// Create Trigger Object
			CronTrigger trigger = new CronTrigger();
			trigger.setName("dailyTrigger");
			
			// trigger.setCronExpression("0 0 12 1/1 * ? *");
			trigger.setCronExpression("	0 0/5 * 1/1 * ? **");

			// schedule
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.getContext().put("username", userName);
			scheduler.getContext().put("password", password);
			scheduler.getContext().put("csvfile", csvFile);
			scheduler.start();
			scheduler.scheduleJob(job, trigger);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
