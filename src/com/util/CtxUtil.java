package com.util;

import com.qh.model.base.AssignFunction;
import com.qh.model.base.Function;
import com.qh.model.base.LoginUser;
import com.qh.model.base.SysCode;
import com.qh.model.user.Position;
import com.qh.service.LoginService;
import com.qh.service.base.AssignFunctionService;
import com.qh.service.base.FunctionService;
import com.qh.service.base.PositionService;
import com.qh.service.base.SysCodeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 系统监听器，做系统启动初始化工作
 *
 * @author Administrator
 */
public class CtxUtil extends ContextLoaderListener {

    private static Log log = LogFactory.getLog(CtxUtil.class);
    //是否是开发模式
    private static boolean devMode = false;
    //配置文件（config_utf-8.properties）
    private static ResourceBundle config = null;
    //数据字典
    private static Map<Integer, SysCode> baseCode = new LinkedHashMap<Integer, SysCode>();
    //功能模块
    private static Map<String,Function> functionSet=new HashMap<String,Function>();
    //角色权限
    private static Map<Integer,Set<Integer>> postionFunSet=new HashMap<Integer, Set<Integer>>();
    //菜单
    private static Map<Integer,List<Function>> memuList = new HashMap<Integer,List<Function>>();

    private static ClassPathXmlApplicationContext ctx = null;
    static {
        try {
            reloadPropertyConfig();//加载配置文件
            reloadSysCode();   //加载数据字典
            reloadFunctionSet();   //加载系统功能模块
            reloadPostionFunSet(null);//重新加载职务权限
            reloadMemuList();//加载一二级菜单
            //初始化清空 在线用户
            getService(LoginService.class).excuteClearOnlineUser();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 重新\加载功能模块
     */
    public static void reloadFunctionSet(){
        FunctionService functionService = CtxUtil.getService(FunctionService.class);
        //获取所有功能模块
        List<Function> list=functionService.getList(null,true,null);
        functionSet.clear();
        //拼凑功能模块
        for(Function f:list){
            if(!StringUtil.isNull(f.getUrl())){
                functionSet.put(f.getUrl().split("\\?")[0],f);
            }
        }
        System.out.println("Init functionSet Data : " + functionSet.size());
    }

    /**
     * 重新\加载职务权限
     */
    public static void reloadPostionFunSet(Integer id){
        AssignFunctionService assignFunctionService = CtxUtil.getService(AssignFunctionService.class);
        List<AssignFunction> list = assignFunctionService.getListByPositionId(id,null,null);
        postionFunSet.clear();
        //拼凑功能权限
        for(AssignFunction af:list){
            Set<Integer> postionSet = postionFunSet.remove(af.getPositionId());
            if(postionSet == null)
                postionSet = new HashSet();
            postionSet.add(af.getFun().getId());
            postionFunSet.put(af.getPositionId(), postionSet);
        }
        System.out.println("Init postionFunSet Data ");
    }

    /**
     * 重新\加载config.properties配置文件
     */
    public static void reloadPropertyConfig() {
        if (config != null)
            config.clearCache();
        config = ResourceBundle.getBundle("config");
        devMode = "true".equals(config.getString("devMode"));
        System.out.println("Init config.properties配置文件");
    }
    /**
     * 加载\重新加载系统缓存数据
     */
    @SuppressWarnings("unchecked")
    public static void reloadSysCode() {
        baseCode.clear();
        List<SysCode> list = CtxUtil.getService(SysCodeService.class).getListByStatus((byte)1);
        for (SysCode c : list) {
            if (c != null) baseCode.put(c.getId(), c);
        }
        System.out.println("Init SysCode Data : "+ list.size());
    }

    public static void  reloadMemuList(){
        AssignFunctionService assignFunctionService = CtxUtil.getService(AssignFunctionService.class);
        PositionService positionService = CtxUtil.getService((PositionService.class));
        List<Position> positionList = positionService.getListByType(null);
        memuList.clear();
       for(Position p : positionList){
           List<AssignFunction> list = assignFunctionService.getListByPositionId(p.getId(),(byte)0,(byte)1);
           List<Function> fList = new ArrayList<Function>();
           for(AssignFunction af:list){
               fList.add(af.getFun());
           }
           memuList.put(p.getId(),fList);
       }
        System.out.println("Init Memu Data : "+memuList.size());
    }

    /**
     * 获取Service
     * @param objClass
     * @param <T>
     * @return
     */
    public static <T> T getService(Class<T> objClass) {
        String s = objClass.getSimpleName();
        return getCurrentWebApplicationContext().getBean(s.replaceFirst(s.substring(0, 1), s.substring(0, 1).toLowerCase()), objClass);
    }


    /**获取功能模块*/
    public static Map<String,Function> getFunctionSet(){
        return functionSet;
    }


    /**
     * 获取登录用户会话数据
     *
     * @return
     */
    public static LoginUser getLoginUser() {
        Object user = null;
        HttpServletRequest req=ServletActionContext.getRequest();
        try {
            user = ServletActionContext.getRequest().getSession().getAttribute(Constants.LOGIN_USER_SESSION_NAME);
        } catch (Exception e) {
			e.printStackTrace();
        }
        if (user != null)
            return (LoginUser) user;
        else {
            return null;
        }
    }

    public static ClassPathXmlApplicationContext getCtx() {
        return ctx;
    }

    public static void setCtx(ClassPathXmlApplicationContext ctx) {
        CtxUtil.ctx = ctx;
    }

    public static ResourceBundle getConfig() {
        return config;
    }

    public static void setConfig(ResourceBundle config) {
        CtxUtil.config = config;
    }

    public static Map<Integer, SysCode> getBaseCode() {
        return baseCode;
    }

    public static void setBaseCode(Map<Integer, SysCode> baseCode) {
        CtxUtil.baseCode = baseCode;
    }

    public static void setFunctionSet(Map<String, Function> functionSet) {
        CtxUtil.functionSet = functionSet;
    }

    public static Map<Integer, Set<Integer>> getPostionFunSet() {
        return postionFunSet;
    }

    public static void setPostionFunSet(Map<Integer, Set<Integer>> postionFunSet) {
        CtxUtil.postionFunSet = postionFunSet;
    }

    public static Map<Integer, List<Function>> getMemuList() {
        return memuList;
    }

    public static void setMemuList(Map<Integer, List<Function>> memuList) {
        CtxUtil.memuList = memuList;
    }
}
