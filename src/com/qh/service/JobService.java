package com.qh.service;

import com.qh.job.ErrorIpJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
* 定时任务服务
*
* @创建人 			王云川
* @创建日期 		2017-03-22
* @修改人 			王云川
* @修改日期 		2017-03-22
* @版本号 			1.0.0
* @版权所有 		XX科技
*/
@org.springframework.stereotype.Service
public class JobService extends BaseService {
	private boolean autoJob = true;
	private SchedulerFactory sf = new StdSchedulerFactory();
	
	//个人定时器任务管理器
	private Scheduler autoTimerScheduler = null;

	
	/**
	 * 启动任务管理器,以系统定时任务
	 * @throws Exception
	 */
	public void run() throws Exception {
		
		autoTimerScheduler = sf.getScheduler();
		JobDetail job = null;
		CronTrigger trigger = null;
		Scheduler sched = sf.getScheduler(); // 初始化调度器
		Date ft = null;
		log.info("------- Scheduling Jobs ----------------");

		//重置personalKey
		//job = JobBuilder.newJob(PersonalKeyJob.class).withIdentity("重置webPortKey---"+CtxUtil.erpProject, "自动任务 - 业务处理---"+CtxUtil.erpProject).build();
		//trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1"+CtxUtil.erpProject, "group1"+CtxUtil.erpProject).withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 * * ? *")).build();
		//ft = sched.scheduleJob(job, trigger);


		//每天晚上删除ip错误记录
		job = JobBuilder.newJob(ErrorIpJob.class).withIdentity("删除ip错误记录", "自动任务 - 业务处理").build();
		trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0 0 01 * * ? *")).build();
		ft = sched.scheduleJob(job, trigger);
		log.info(job.getKey() + "启动 expression: " + trigger.getCronExpression());

			
//		<Parameter name="autoJob" value="false" override="false"/>	
		if(autoJob){	
			sched.start(); // 开启调度任务，执行作业
			log.info("------- Started Scheduler （定时任务管理器） successfully, Waiting five minutes... ------------");
			autoTimerScheduler.start();
		}else{
			System.out.println("=======定时任务没有启动！！！");
		}
		
	}

	/**
	 * 删除定时任务
	 * @param name
	 * @param group
	 */
	public void deleteJob(String name,String group){
		JobKey j = JobKey.jobKey(name, group);
		try {
			boolean rel = autoTimerScheduler.deleteJob(j);
			log.info("delete Job:"+j+",status:"+rel);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	
	public Scheduler getAutoTimerScheduler() {
		return autoTimerScheduler;
	}

	public void setAutoTimerScheduler(Scheduler autoTimerScheduler) {
		this.autoTimerScheduler = autoTimerScheduler;
	}


	public boolean isAutoJob() {
		return autoJob;
	}


	public void setAutoJob(boolean autoJob) {
		this.autoJob = autoJob;
	}
	



}