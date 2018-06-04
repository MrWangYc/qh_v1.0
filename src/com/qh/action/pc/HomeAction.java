package com.qh.action.pc;

import com.qh.action.BaseAction;
import com.qh.service.base.FunctionService;
import com.util.CtxUtil;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 系统主页
 * @创建人 			王云川
 * @创建日期 		2017-09-25
 * @修改人 			王云川
 * @修改日期 		2017-09-25
 * @版本号 			1.0.0
 * @版权所有 		XX科技
 */
@Results({
        @Result(name = "home", type = "dispatcher", location = "/WEB-INF/jsp/pc/home.jsp"),
})
public class HomeAction extends BaseAction {
    @Autowired
    private FunctionService functionService;
    /**
     * PC版首页
     * @return
     */
    @Action("home")
    public String index(){
//        CtxUtil.reloadMemuList();
//        req.setAttribute("fList", CtxUtil.getMemuList().get(CtxUtil.getLoginUser().getPosition().getId()));
        req.setAttribute("userName", CtxUtil.getLoginUser().getEmployee().getName());
        req.setAttribute("companyName",CtxUtil.getLoginUser().getEmployee().getCompany().getName());
        return "home";
    }
}
