package com.qh.action.pc.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qh.action.BaseAction;
import com.qh.model.base.Function;
import com.qh.model.user.Employee;
import com.qh.model.user.MemCate;
import com.qh.model.user.Membership;
import com.qh.service.base.AssignFunctionService;
import com.qh.service.base.FunctionService;
import com.qh.service.base.PositionService;
import com.qh.service.user.CompanyService;
import com.qh.service.user.EmployeeService;
import com.qh.service.user.MembershipService;
import com.util.CtxUtil;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.lang.reflect.Member;
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
@AllowedMethods(value={("edit"),("del"),("getMembershipByPhone"),("check")})
public class MembershipAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private MembershipService objectService;

	//功能模块
	private Membership object = null;
    //查询参数
    private Map<String, String> s = new HashMap<String, String>();
	private Integer id;

	private List<Membership> cList = new ArrayList<Membership>();

	private List<Membership> eList = new ArrayList<Membership>();
	/**
	 * 数据列表
	 * @return
	 * @throws IOException 
	 */
	public String list() throws IOException {
		if("json".equals(flag)){//加载数据
			if(object == null)
				object = new Membership();
			res.getWriter().println(objectService.getJsonArray(s,page,rows));
			return NONE;
		}else if("exportExcel".equals(flag)){
			return NONE;
		}else{//加载界面
		}
		return "list";
	}

	public String getMembershipByPhone() throws IOException {
		String phone = object.getPhone();
		JSONArray a = new JSONArray();
		List<Membership> membershipList = new ArrayList<Membership>();
		if(phone!=""&&!"".equals(phone)){
			List<Membership> list = objectService.getListByPhone(phone);
			for(Membership m:list){
				if(!membershipList.contains(m)){
					membershipList.add(m);
				}
				if(m.getParent()!=null){
					if(!membershipList.contains(m.getParent())){
						membershipList.add(m.getParent());
					}
					if(m.getParent().getChildCodes()!=null){
						for(Membership m1:m.getParent().getChildCodes()){
							if(!membershipList.contains(m1)){
								membershipList.add(m1);
							}
						}
					}
				}
			}
		}
		for(Membership m:membershipList){
			JSONObject object = new JSONObject();
			object.put("id",m.getId());
			object.put("phone",m.getPhone());
			object.put("name",m.getName());
			a.add(object);
		}
		res.getWriter().println(a.toJSONString());
		return NONE;
	}

	public String check(){
	    return "check";
    }


	/**
	 * 添加、修改
	 * @return
	 */

	public String edit() {
 		if("add".equals(flag)){
 			req.setAttribute("now",new Date());
			flag = "save";
		}else if("save".equals(flag)){
			object.setCreateDate(new Date());
			object.setType("0");
			object.setCreateEmployee(CtxUtil.getLoginUser().getEmployee()!=null?CtxUtil.getLoginUser().getEmployee():null);
			objectService.save(object);
			for(Membership m:cList){
				if(m!=null){
					m.setParent(object);
					m.setCreateEmployee(CtxUtil.getLoginUser().getEmployee()!=null?CtxUtil.getLoginUser().getEmployee():null);
					m.setCreateDate(new Date());
					objectService.save(m);
				}
			}
			return NONE;
		}else if("edit".equals(flag)){
			object = objectService.getObject(object.getId());
			cList = objectService.getListByParent(object.getId(),"1");
			eList = objectService.getListByParent(object.getId(),"2");
			flag = "update";
		}else if("update".equals(flag)){
 			object.setUpdateDate(new Date());
 			object.setUpdateEmp(CtxUtil.getLoginUser().getEmployee()!=null?CtxUtil.getLoginUser().getEmployee():null);
			objectService.update(object);
			for(Membership m:cList){
				if(m!=null){
					if(m.getParent()==null){
						m.setParent(object);
						m.setCreateEmployee(CtxUtil.getLoginUser().getEmployee()!=null?CtxUtil.getLoginUser().getEmployee():null);
						m.setCreateDate(new Date());
						objectService.save(m);
					}else {
						m.setUpdateDate(new Date());
						m.setUpdateEmp(CtxUtil.getLoginUser().getEmployee()!=null?CtxUtil.getLoginUser().getEmployee():null);
						objectService.update(m);
					}
				}
			}
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


	public Membership getObject() {
		return object;
	}

	public void setObject(Membership object) {
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

	public List<Membership> getcList() {
		return cList;
	}

	public void setcList(List<Membership> cList) {
		this.cList = cList;
	}

	public List<Membership> geteList() {
		return eList;
	}

	public void seteList(List<Membership> eList) {
		this.eList = eList;
	}
}
