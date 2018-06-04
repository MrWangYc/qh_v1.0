package com.qh.action.pc.base;

import com.qh.action.BaseAction;
import com.qh.model.base.SysCode;
import com.qh.service.base.SysCodeService;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
* 系统功能模块管理
* @创建人 			王云川
* @创建日期 		2017-09-25
* @修改人 			王云川
* @修改日期 		2017-09-25
* @版本号 			1.0.0
* @版权所有 		XX科技
*/
@AllowedMethods(value={("edit"),("del")})
public class SysCodeAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private SysCodeService sysCodeService;
	//功能模块
	private SysCode object = null;
	//查询参数
	private Map<String, String> s = new HashMap<String, String>();
	private Integer id;

	/**
	 * 数据列表
	 * @return
	 * @throws IOException 
	 */
	public String list() throws IOException {
		System.out.println(s);
		if("json".equals(flag)){//加载数据
			if(object == null)
				object = new SysCode();
			res.getWriter().println(sysCodeService.getJsonArray(id,s));
			return NONE;
		}else if("exportExcel".equals(flag)){
			return NONE;
		}else{//加载界面
		}
		if("treeFrame".equals(flag)){
			req.setAttribute("fList",sysCodeService.getListByParentId(id));
			return "treeFrame";
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
			sysCodeService.save(object);
			return NONE;
		}else if("edit".equals(flag)){
			object = (SysCode) sysCodeService.getObject(SysCode.class,object.getId());
			flag = "update";
		}else if("update".equals(flag)){
			sysCodeService.update(object);
			return NONE;
		}
		return "edit"; 
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String del() throws Exception{
		sysCodeService.delete(object);
		res.getWriter().println("删除成功！");
		return NONE;
	}


	public SysCode getObject() {
		return object;
	}

	public void setObject(SysCode object) {
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
