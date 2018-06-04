package com.util.tag;

import com.qh.model.base.LoginUser;
import com.qh.service.BaseService;
import com.opensymphony.xwork2.ActionContext;
import com.util.Constants;
import com.util.CtxUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 功能权限过滤
 * @author Administrator
 */
public class PopedomTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 子功能主键编号
	 */
	public Integer cid = null; 
	public Boolean test = null;
	
	public int doStartTag() throws JspException {
		//判断参数必须是一个
		if(cid == null && test == null)
			new Exception("参数cid和test至少要填一项！");
		Object lu = ActionContext.getContext().getSession().get(Constants.LOGIN_USER_SESSION_NAME);
		if(lu != null){
			LoginUser loginUser = (LoginUser)lu;
			if("system".equals(loginUser.getEmployee().getName()) && (test == null ? true : test.booleanValue()))
				return EVAL_BODY_INCLUDE;
			else if(CtxUtil.getService(BaseService.class).auth(cid)){//判断是否在权限范围内
				return Tag.EVAL_BODY_INCLUDE;
			}
		}
		return Tag.SKIP_BODY;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Boolean getTest() {
		return test;
	}

	public void setTest(Boolean test) {
		this.test = test;
	}
	
}
