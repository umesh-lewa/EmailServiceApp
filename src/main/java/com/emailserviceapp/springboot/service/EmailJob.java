package com.emailserviceapp.springboot.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.mail.MessagingException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;

public class EmailJob implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {

		System.out.println("Hello Quartz!");
		System.out.println("inside EmailJob class....!!!");
		String jcp = "config.json";

		try {

			SchedulerContext schedulerContext = context.getScheduler().getContext();
			// gets the value from context.

			String usernameFromContext = (String) schedulerContext.get("username");
			String passwordFromContext = (String) schedulerContext.get("password");
			File csvFile = (File) schedulerContext.get("csvfile");

			System.out.println("Email address inside Emailjob class : " + usernameFromContext);
			System.out.println("Password inside Emailjob class :" + passwordFromContext);

			JSONReader jsonReader = new JSONReader(Paths.get(jcp));

			// List<Subscriber> subscribers = CSVReader.parseCSV(jsonReader.subcsvpath);
			List<EmailInfo> emailInfoList = CSVReader.parseCSV(csvFile);

			// String emailContents = new
			// String(Files.readAllBytes(Paths.get(jsonReader.htmlpath)));

			String emailContents;

			for (EmailInfo emailInfo : emailInfoList) {

				System.out.println("Sending new post email to: " + emailInfo.getEmail());

				emailContents = "Hey [" + emailInfo.getFirstname() + "],<br />" + "<br />"
						+ "Kindly confirm if the following details are correct.<br />" + " <br />" + "["
						+ emailInfo.getFirstname() + "] [" + emailInfo.getLastname() + "]<br />" + "["
						+ emailInfo.getEmail() + "]<br />" + "[" + emailInfo.getPhone() + "]<br />" + "[Address]"
						+ "[" + emailInfo.getGender() + "]<br />" + "<br />" + "Regards,<br />" + "<br />" + "["
						+ emailInfo.getUsername() + "]<br />" + "<br />"
						+ "---------------------------------------------------<br />" + "Thanks";

				// EmailEngine.generateAndSendEmail(jsonReader.host, jsonReader.port,
				// jsonReader.user, jsonReader.password,
				// jsonReader.subject, emailContents, subscriber.getEmail());

				EmailEngine.generateAndSendEmail(jsonReader.host, jsonReader.port, usernameFromContext,
						passwordFromContext, jsonReader.subject, emailContents, emailInfo.getEmail());

			}

		} catch (IOException | MessagingException ex) {
			ex.printStackTrace(System.err);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
