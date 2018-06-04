package com.qh.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qh.model.base.LoginUser;
import com.opensymphony.xwork2.ActionSupport;
import com.util.Constants;
import com.util.CtxUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Action基类，实现Action基本处理方法和逻辑
 *
 * @author Administrator
 */
@AllowedMethods
public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, SessionAware {
    private static final long serialVersionUID = 1L;
    public static Log log = LogFactory.getLog(BaseAction.class);
    public HttpServletRequest req;
    public HttpServletResponse res;
    public Map params = null;
    public Map session;
    public CtxUtil ctxUtil = new CtxUtil();
    //当前项目根目录
    public String contextPath;
    //列表查询条件
    public Map<String, Object> queryParams = new HashMap<String, Object>();
    // 以下是页面表单数据和布局用到的参数
    public String order = "";    //升序还是降序
    public String sort = "";//排序字段
    public String flag = null;
    public int page = 1;//列表查询当前页数
    public int rows = 10;//列表查询每页行数
    public int loadType = 2;//查询方式，0：只查询列表，1：只统计，2：查询统计,3:其它


    //=========================默认请求常量==============================
    public static final int REQUEST_SUCCEED=1;//请求成功
    public static final int REQUEST_ERROR=0;//请求失败
    public static final int REQUEST_OTHER_ERROR=-1;//请求其它错误
    public static final int REQUEST_VIEW_CODE=-2;//请求其它错误
    public static final int REQUEST_ERROR_CODE=-3;//请求其它错误

    public HttpServletRequest getReq() {
        return req;
    }

    public void setReq(HttpServletRequest req) {
        params = req.getParameterMap();
        this.req = req;
    }

    public HttpServletResponse getRes() {
        return res;
    }

    public void setRes(HttpServletResponse res) {
        this.res = res;
    }

    public String getOrder() {
        return order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Map getSession() {
        return session;
    }

    public Map getParams() {
        return params;
    }


    public void setParams(Map params) {
        this.params = params;
    }

    public void setServletRequest(HttpServletRequest req) {
        this.req = req;
    }

    public void setSession(Map session) {
        this.session = session;

    }
    public void setServletResponse(HttpServletResponse res) {
        this.res = res;
    }


    public String getJsonString(boolean b) {
        JSONObject json = new JSONObject();
        json.put("flag", b);
        return json.toString();
    }

    public String getJsonString(boolean b, String errorRemark) {
        JSONObject json = new JSONObject();
        if (b)
            json.put("flag", true);
        else {
            json.put("flag", false);
            if (errorRemark != null && errorRemark.trim().length() > 0)
                json.put("remark", errorRemark.trim());
        }
        return json.toString();
    }

    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }


    /**
     * 页面输出字符
     * @param str   返回指
     */
    public void writer(Object str) {
        try {
            res.setCharacterEncoding("utf-8");
            res.setContentType("text/html");
            res.getWriter().print(str.toString());
            res.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 页面输出字符
     * @param state 返回状态
     */
    public void writerJSON(int state,String msg) {
        JSONObject result=new JSONObject();
        result.put("state",state);
        if(msg != null)
            result.put("msg",msg);
        writer(result);
    }

    /**
     * 页面输出字符
     * @param state 返回状态
     * @param object    返回的JSONobject参数
     */
    public void writerJSON(int state,String msg,JSONObject object) {
        JSONObject result=new JSONObject();
        result.put("state",state);
        if(msg != null)
            result.put("msg",msg);
        result.put("data",object);
        writer(result);
    }

    /**
     * 页面输出字符
     * @param state 返回状态
     * @param array 返回的JSONarray参数
     */
    public void writerJSON(int state,String msg,JSONArray array) {
        JSONObject result=new JSONObject();
        result.put("state",state);
        if(msg != null)
            result.put("msg",msg);
        result.put("data",array);
        writer(result);
    }

    /**
     * 获取用户会话对象 Session名称s_user
     *
     * @return
     */
    public LoginUser getLoginUser() {
        Object user = session.get(Constants.LOGIN_USER_SESSION_NAME);
        if (user != null)
            return (LoginUser) user;
        else
            return null;
    }

    public Map<String, Object> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<String, Object> queryParams) {
        this.queryParams = queryParams;
    }

    public String getContextPath() {
        return req.getContextPath();
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public CtxUtil getCtxUtil() {
        return ctxUtil;
    }

    public void setCtxUtil(CtxUtil ctxUtil) {
        this.ctxUtil = ctxUtil;
    }

}
