package org.easyframework.easyreport.scheduler;

import java.util.Timer;

import org.apache.log4j.PropertyConfigurator;
import org.easyframework.report.data.util.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class StartUp {
	private static final Logger logger = LoggerFactory.getLogger(StartUp.class);

	public static void main(String[] args) {
		try {
			PropertyConfigurator.configure("config/log4j.properties");
			SpringContextUtils springContextUtil = new SpringContextUtils();
			ApplicationContext appContext = new FileSystemXmlApplicationContext("config/app-config.xml");
			springContextUtil.setApplicationContext(appContext);
			Timer timer = new Timer();
			timer.schedule(new MailSendTask(), 0, 1000);
			timer.schedule(new ReloadDataTask(), 0, 60 * 60 * 1000);
		} catch (Exception ex) {
			logger.error("", ex);
		}
	}
}
