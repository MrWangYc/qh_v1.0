package com.qh.action.pc.base;

import com.qh.action.BaseAction;
import com.qh.model.base.Function;
import com.qh.model.user.Position;
import com.qh.service.base.AssignFunctionService;
import com.qh.service.base.FunctionService;
import com.qh.service.base.PositionService;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 系统功能模块管理
* @创建人 			王云川
* @创建日期 		2017-09-19
* @修改人 			王云川
* @修改日期 		2017-09-19
* @版本号 			1.0.0
* @版权所有 		XX科技
*/
@AllowedMethods(value={("edit"),("del"),("auth")})
public class PositionAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private PositionService objectService;
	@Autowired
	private FunctionService functionService;
	@Autowired
	private AssignFunctionService assignFunctionService;
	//功能模块
	private Position object = null;
    //查询参数
    private Map<String, String> s = new HashMap<String, String>();
	private Integer id;

	private List<Function> fun = new ArrayList<Function>();
	
	/**
	 * 数据列表
	 * @return
	 * @throws IOException 
	 */
	public String list() throws IOException {
		if("json".equals(flag)){//加载数据
			if(object == null)
				object = new Position();
			res.getWriter().println(objectService.getJsonArray(s));
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
			objectService.save(object);
			return NONE;
		}else if("edit".equals(flag)){
			object = objectService.getObject(object.getId());
			flag = "update";
		}else if("update".equals(flag)){
			objectService.update(object);
			return NONE;
		}
		return "edit"; 
	}

	/**
	 * 权限分配
	 * @return
	 */
	public String auth(){
		//所有启用的一级菜单权限
		req.setAttribute("fList",functionService.getListByStatus(true,(byte)0));
		if("save".equals(flag)){
			if(fun!=null&&fun.size()>0&&id!=null){
				assignFunctionService.saveAssignFunction(fun,id);
				return NONE;
			}
		}
		if(id!=null)
			req.setAttribute("afList",assignFunctionService.getListByPositionId(id,null,null));
		return "auth";
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


	public Position getObject() {
		return object;
	}

	public void setObject(Position object) {
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

	public List<Function> getFun() {
		return fun;
	}

	public void setFun(List<Function> fun) {
		this.fun = fun;
	}
}
