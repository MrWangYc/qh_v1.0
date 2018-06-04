package com.util.listener;


import com.qh.service.JobService;
import com.util.CtxUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author yunwen
 */
public class ServletListener implements ServletContextListener {


    static Log log = LogFactory.getLog(ServletListener.class);

    /* (non-Javadoc)
     * 系统初始化
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event) {
        //关闭定时任务
//    	<Context path="/rerp" docBase="F:\Workspaces\rerp\WebRoot" >
//			<Parameter name="autoJob" value="false" override="false"/>
//	 	</Context>
        String autoJob = event.getServletContext().getInitParameter("autoJob");
        CtxUtil.getCtx();
        System.out.println("=======getInitParameter(\"autoJob\") 定时任务：" + autoJob);
        //启动定时任务
        try {
            JobService job = CtxUtil.getService(JobService.class);
            if ("false".equals(autoJob)) {
                job.setAutoJob(false);
            }
            job.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //存放系统配置参数到Servlet容器
    }

    public void contextDestroyed(ServletContextEvent arg0) {
        log.info("Servlet容器销毁！");
        //IPSeeker.getInstance().close();
    }

}
