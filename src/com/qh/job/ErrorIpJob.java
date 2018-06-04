package com.qh.job;

import com.qh.service.base.ErrorIpService;
import com.util.CtxUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ErrorIpJob implements Job {
//	private static Logger _log = LoggerFactory.getLogger(TestJob.class);
	public void execute(JobExecutionContext context)throws JobExecutionException // 具体的作业
	{
//		JobKey jobKey = context.getJobDetail().getKey();
		ErrorIpService errorIpService = CtxUtil.getService(ErrorIpService.class);
		errorIpService.delete();
//		_log.info("===============SimpleJob says: " + jobKey + " executing at " + new Date());
		//System.out.println("执行TestJob任务");
	}

}
