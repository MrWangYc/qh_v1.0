package com.qh.action.pc.base;

import com.qh.action.BaseAction;
import com.qh.model.base.Function;
import com.qh.service.base.FunctionService;
import com.util.CtxUtil;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
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
@AllowedMethods(value={("edit"),("del")})
public class FunctionAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private FunctionService functionService;
	//功能模块
	private Function object = null;
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
				object = new Function();
			res.getWriter().println(functionService.getJsonArray(id,s));
			return NONE;
		}else if("exportExcel".equals(flag)){
			return NONE;
		}else{//加载界面
		}
		if("treeFrame".equals(flag)){
			req.setAttribute("fList",functionService.getMenuList(true,null));
			return "treeFrame";
		}
		return "list";
	}

	/**
	 * 添加、修改
	 * @return
	 */

	public String edit() {
		req.setAttribute("fList",functionService.getMenuList(null,null));
		if("add".equals(flag)){
			flag = "save";
		}else if("save".equals(flag)){
			functionService.save(object);
			CtxUtil.reloadFunctionSet();
			return NONE;
		}else if("edit".equals(flag)){
			object = functionService.getObject(object.getId());
			flag = "update";
		}else if("update".equals(flag)){
			functionService.update(object);
			CtxUtil.reloadFunctionSet();
			return NONE;
		}
		return "edit"; 
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String del() throws Exception{
		functionService.delete(object);
		res.getWriter().println("删除成功！");
		return NONE;
	}

	
	public Function getObject() {
		return object;
	}
	
	public void setObject(Function object) {
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
