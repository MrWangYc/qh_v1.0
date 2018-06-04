package com.qh.action.pc.user;

import com.qh.action.BaseAction;
import com.qh.model.base.Function;
import com.qh.model.user.Category;
import com.qh.model.user.Employee;
import com.qh.service.base.AssignFunctionService;
import com.qh.service.base.FunctionService;
import com.qh.service.base.PositionService;
import com.qh.service.user.CategoryService;
import com.qh.service.user.CompanyService;
import com.qh.service.user.EmployeeService;
import com.util.CtxUtil;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.*;

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
public class CategoryAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CategoryService objectService;

	//功能模块
	private Category object = null;
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
				object = new Category();
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
		if("add".equals(flag)){
			flag = "save";
		}else if("save".equals(flag)){
			object.setCreateDate(new Date());
			object.setCreateEmployee(CtxUtil.getLoginUser().getEmployee()!=null?CtxUtil.getLoginUser().getEmployee():null);
			objectService.save(object);
			return NONE;
		}else if("edit".equals(flag)){
			object = objectService.getObject(object.getId());
			flag = "update";
		}else if("update".equals(flag)){
			object.setUpdateDate(new Date());
			object.setUpdateEmp(CtxUtil.getLoginUser().getEmployee()!=null?CtxUtil.getLoginUser().getEmployee():null);
			objectService.update(object);
			return NONE;
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


	public Category getObject() {
		return object;
	}

	public void setObject(Category object) {
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

}
