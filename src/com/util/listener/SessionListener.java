package com.util.listener;

import com.qh.model.base.OnlineUser;
import com.qh.service.LoginService;
import com.util.Constants;
import com.util.CtxUtil;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    /**
     * 创建SESSION
     * @param arg0
     */
    public void sessionCreated(HttpSessionEvent arg0) {
        //判断Seesion是否有默认城市，没有则默认设置一个
        HttpSession session = arg0.getSession();
        //获取默认城市
//        String cityId= (String) session.getAttribute(Constants.SESSION_CITY);
//        if(StringUtil.isNull(cityId)){//设置默认城市
//            System.out.println("=================>用户使用系统");
//            session.setAttribute(Constants.SESSION_CITY,"1010");
//        }
    }

    /**
     * SESSION销毁
     * @param arg0
     */
    public void sessionDestroyed(HttpSessionEvent arg0) {
        HttpSession session = arg0.getSession();
        Object user = session.getAttribute(Constants.LOGIN_USER_SESSION_NAME);
        if (user == null)
            return;
        //用户下线 return (LoginUser) user;
        LoginService loginService= CtxUtil.getService(LoginService.class);
        OnlineUser ou = loginService.getObjectBySid(session.getId());
        //用户下线
        loginService.userLonger(ou);
        session.removeAttribute(Constants.LOGIN_USER_SESSION_NAME);
        System.out.println("=================>用户自动");

    }
}
