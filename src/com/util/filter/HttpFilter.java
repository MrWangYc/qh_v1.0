package com.util.filter;


import com.alibaba.fastjson.JSONObject;
import com.util.Constants;
import com.util.CtxUtil;
import com.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;

public class HttpFilter implements Filter {
    public static Log log = LogFactory.getLog(HttpFilter.class);

    /**未登录*/
    public static final int HTTP_LOGIN =1;
    /**非法请求*/
    public static final int HTTP_ILLEGAL =2;
    /**没有权限操作*/
    public static final int HTTP_NO_LIMITS =3;


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        //设置返回编码格式
        res.setCharacterEncoding("utf-8");
        res.setContentType("text/html");

        //当前项目根目录
        String contextPath = req.getContextPath();
        //访问地址
        String reqUri = req.getRequestURI().replace(Constants.PROJECT_ROOT,"");
        System.out.println("====================>>"+reqUri);
        //判断是否登录了系统
        boolean isLogin = req.getSession().getAttribute(Constants.LOGIN_USER_SESSION_NAME) == null ? false : true;
        //请求方式
        String method=req.getMethod();
        //获取默认城市
        String cityId= (String) req.getSession().getAttribute(Constants.SESSION_CITY);
        if(StringUtil.isNull(cityId)){//设置默认城市
            req.getSession().setAttribute(Constants.SESSION_CITY,"1010");
        }

        if(!"/login.action".equals(reqUri)&&!"/userLogin.action".equals(reqUri)){
            if(!isLogin) {
                res.sendRedirect(contextPath + "/login.action");
                return;
            }
        }
       /* if(!isLogin){//未登录系统
            if("post".equals(method)) {//返回登录提示
                res.getWriter().println(getResultJSON(HTTP_LOGIN));
                return;
            }else{//跳转到登录界面
                res.sendRedirect(Constants.PROJECT_ROOT+"/login.action");
                return;
            }
        }*/

      /*  LoginUser user = CtxUtil.getLoginUser();
        if(user == null){

        }*/
       /* if(CtxUtil.getLoginUser().getEmployee()==null){

        }*/


//        if(!CtxUtil.getFunctionSet().containsKey(reqUri)){  //非法请求
//           res.getWriter().println(getResultJSON(HTTP_ILLEGAL));return;
//        }else{
            /*Function f=CtxUtil.getFunctionSet().get(reqUri);
            if(f.getValidate() == true){//需要登录
                if(!isLogin){//未登录系统
                    if("post".equals(method)) {//返回登录提示
                        res.getWriter().println(getResultJSON(HTTP_LOGIN));
                        return;
                    }else{//跳转到登录界面
                        res.sendRedirect(Constants.PROJECT_ROOT+"/login.action");
                        return;
                    }
                }
                //当前登录对象
                LoginUser u = (LoginUser) req.getSession().getAttribute(Constants.LOGIN_USER_SESSION_NAME);
                if(!isPostionFunSet(u.getEmployee().getPosition().getId(),f.getId(),u.getEmployee().getPhone())){//没有权限访问
                    res.getWriter().println(getResultJSON(HTTP_NO_LIMITS));
                   return;
                }
            }*/
//        }

        System.out.println(">>>>>>>>>>>>>>>"+contextPath+"==="+reqUri);
        chain.doFilter(req, res);
    }

    /**
     * 根据功能ID，判断此职务是否有这个功能权限
     * @param posId 职务
     * @param funId 功能ID
     * @param phone 电话号码
     * @return
     */
    public static boolean isPostionFunSet(Integer posId,Integer funId,String phone){
        boolean result=false;
        if((CtxUtil.getPostionFunSet().containsKey(posId) && CtxUtil.getPostionFunSet().get(posId).contains(funId)) || (phone !=null && phone.equals(Constants.ADMIN_PHONE))){
            result=true;
        }
        return result;
    }




    /**
     * 获取返回结果JSON
     * @param type  返回类型
     * @return
     */
    public static JSONObject getResultJSON(int type){
        JSONObject o=new JSONObject();
        o.put("state",type);
        switch (type) {
            case 1:
                o.put("msg", "未登录！");break;
            case 2:
                o.put("msg", "非法请求！");break;
            case 3:
                o.put("msg", "没有权限操作！");break;
        }
        return o;
    }


    public void init(FilterConfig filterConfig) throws ServletException {
        //记录服务器启动时间
        filterConfig.getServletContext().setAttribute("tomcatStartDate", new Date());
    }

    public void destroy() {
        System.out.println("销毁系统过滤器。");
    }


    /**
     * 替换安全特殊符号
     *
     * @param map
     */
    public boolean replaceSafeCommand(Map<String, String[]> map) {
//		第二种是采用正则表达式将包含有 单引号(')，分号(;) 和 注释符号(--)的语句给替换掉来防止SQL注入
//		return str.replaceAll(".*([';]+|(--)+).*", " ");
//		return str.replaceAll("([';])+|(--)+","");
        String temp = null;
        if (map == null) return true;
        for (Entry<String, String[]> p : map.entrySet()) {
            String[] newValue = p.getValue();
            for (int i = 0; i < newValue.length; i++) {
                if (p.getKey() != null && p.getKey().toLowerCase().indexOf("ProcessBuilder".toLowerCase()) > -1) {
                    map.remove(p.getKey());
                    System.err.println("非法指令被移除:" + p.getKey());
                    return false;
                }

                if (newValue[i] == null || "".equals(newValue[i])) continue;
                temp = newValue[i].toString();
                
//				//处理'
//				if(newValue[i].indexOf("") > -1)
				newValue[i] = newValue[i].replaceAll("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", "");
//				//处理--
//				if(newValue[i].indexOf("--") > -1)
//					newValue[i] = newValue[i].replaceAll("-", "－");
                //处理()
                if (newValue[i].toString() != temp)
                    System.err.println("安全替换：" + p.getKey() + "中存在“" + newValue[i] + "”已被替换！");
            }
            map.put(p.getKey(), newValue);
        }
        return true;
    }


    class ParameterRequestWrapper extends HttpServletRequestWrapper {
        private Map<String, String[]> params;

        public ParameterRequestWrapper(HttpServletRequest request, Map<String, String[]> newParams) {
            super(request);
            this.params = newParams;
        }

        @Override
        public String getParameter(String name) {
            String result = "";
            Object v = params.get(name);
            if (v == null) {
                result = null;
            } else if (v instanceof String[]) {
                String[] strArr = (String[]) v;
                if (strArr.length > 0) {
                    result = strArr[0];
                } else {
                    result = null;
                }
            } else if (v instanceof String) {
                result = (String) v;
            } else {
                result = v.toString();
            }
            return result;
        }

        @Override
        public Map getParameterMap() {
            return params;
        }

        @Override
        public Enumeration getParameterNames() {
            return new Vector(params.keySet()).elements();
        }

        @Override
        public String[] getParameterValues(String name) {
            String[] result = null;
            Object v = params.get(name);
            if (v == null) {
                result = null;
            } else if (v instanceof String[]) {
                result = (String[]) v;
            } else if (v instanceof String) {
                result = new String[]{(String) v};
            } else {
                result = new String[]{v.toString()};
            }
            return result;
        }
    }

    class ResponseWrapper extends HttpServletResponseWrapper {
        private PrintWriter cachedWriter;
        private CharArrayWriter bufferedWriter;

        public ResponseWrapper(HttpServletResponse response) {
            super(response);
            // 这个是我们保存返回结果的地方
            bufferedWriter = new CharArrayWriter();
            // 这个是包装PrintWriter的，让所有结果通过这个PrintWriter写入到bufferedWriter中
            cachedWriter = new PrintWriter(bufferedWriter);
        }

        @Override
        public PrintWriter getWriter() {
            return cachedWriter;
        }

        /**
         * 获取原始的HTML页面内容。
         *
         * @return
         */
        public String getResult() {
            return bufferedWriter.toString();
        }
    }

}
