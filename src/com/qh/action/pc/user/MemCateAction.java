package com.qh.action.pc.user;

import com.qh.action.BaseAction;
import com.qh.model.user.Category;
import com.qh.model.user.MemCate;
import com.qh.model.user.Membership;
import com.qh.service.user.CategoryService;
import com.qh.service.user.MemCateService;
import com.qh.service.user.MembershipService;
import com.util.CtxUtil;
import com.util.DateUtil;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* 系统功能模块管理
* @创建人 			王云川
* @创建日期 		2017-09-19
* @修改人 			王云川
* @修改日期 		2017-09-19
* @版本号 			1.0.0
* @版权所有
*/
@AllowedMethods(value={("edit"),("del"),("auth")})
public class MemCateAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private MemCateService objectService;

	@Autowired
	private CategoryService categoryService;

	//功能模块
	private MemCate object = null;

	private Membership membership = null;

    //查询参数
    private Map<String, String> s = new HashMap<String, String>();
	private Integer id;

	
	/**
	 * 数据列表
	 * @return
	 * @throws IOException 
	 */
	public String list() throws IOException {
		if("json".equals(flag)){//加载数据
			if(object == null)
				object = new MemCate();
			res.getWriter().println(objectService.getJsonArray(s,page,rows));
			return NONE;
		}else if("exportExcel".equals(flag)){
			return NONE;
		}else{//加载界面
		}
		return "list";
	}

	/**
	 * 添加、修改
	 * @return
	 */

	public String edit() {
		req.setAttribute("cList",categoryService.getListByType("1"));
		req.setAttribute("now",new Date());
		if("add".equals(flag)){
			flag = "save";
		}else if("save".equals(flag)){
			object.setCreateDate(new Date());
			object.setCreateEmp(CtxUtil.getLoginUser().getEmployee()!=null?CtxUtil.getLoginUser().getEmployee():null);
			//判断数据有效性
			if("1".equals(object.getType())){
				object.setRemainder(object.getTotalDegree());
			}else if("2".equals(object.getType())){
				object.setEndDate(DateUtil.getAjustDate(object.getStartDate(),30));
			}else if("3".equals(object.getType())) {
				object.setEndDate(DateUtil.getAjustDate(object.getStartDate(),90));
			}else if("4".equals(object.getType())){
				object.setEndDate(DateUtil.getAjustDate(object.getStartDate(),360));
			}
			objectService.save(object);
			return NONE;
		}else if("edit".equals(flag)){
			object = objectService.getObject(object.getId());
			flag = "update";
		}else if("update".equals(flag)){
			objectService.update(object);
			return NONE;
		}else if("use".equals(flag)){
			object = objectService.getObject(object.getId());
			flag="save";
			return "use";
		}
		return "edit";
	}

	
	/**
	 * 删除
	 * @return
	 */
	public String del() throws Exception{
		objectService.delete(object);
		res.getWriter().println("删除成功！");
		return NONE;
	}


	public MemCate getObject() {
		return object;
	}

	public void setObject(MemCate object) {
		this.object = object;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Map<String, String> getS() {
		return s;
	}

	public void setS(Map<String, String> s) {
		this.s = s;
	}


	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}
}
