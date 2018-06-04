package com.qh.dao.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qh.dao.BaseDao;
import com.qh.model.user.Employee;
import com.qh.model.user.Membership;
import com.util.Constants;
import com.util.CtxUtil;
import com.util.DateUtil;
import com.util.StringUtil;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* 系统功能模块配置，一级菜单、二级菜单、子功能权限配置
* @创建人 			王云川
* @创建日期 		2017-09-19
* @修改人 			王云川
* @修改日期 		2017-09-19
* @版本号 			1.0.0
* @版权所有
*/
@Repository
public class MembershipDao extends BaseDao {
	/**
	 * 获取数据并显示到界面上去,将无法直接看懂的参数直接转化为对应的数据
	 * @param s
	 * @return
	 */
	public JSON getJsonList(Map<String, String> s, Integer page, Integer limit)throws IOException {
		JSONArray array = new JSONArray();
		List<Membership> list = new ArrayList<Membership>();
		List<Membership> count = new ArrayList<Membership>();
		StringBuffer sb = new StringBuffer();
		if(!StringUtil.isNull(s.get("name")))
			sb.append(" and name like '%"+ URLDecoder.decode(s.get("name"),"UTF-8")+"%' ");
		if(!StringUtil.isNull(s.get("phone")))
			sb.append(" and Phone like '%"+ URLDecoder.decode(s.get("phone"),"UTF-8")+"%' ");
		if("1".equals(CtxUtil.getLoginUser().getEmployee().getPosition().getDataAuth())|| Constants.ADMIN_PHONE.equals(CtxUtil.getLoginUser().getEmployee().getPhone())){

		}else if("2".equals(CtxUtil.getLoginUser().getEmployee().getPosition().getDataAuth())){
			sb.append(" and createEmployee.company.id = "+CtxUtil.getLoginUser().getEmployee().getCompany().getId());
		}else if("3".equals(CtxUtil.getLoginUser().getEmployee().getPosition().getDataAuth())){
			sb.append(" and createEmployee.id = "+CtxUtil.getLoginUser().getEmployee().getId());
		}else
			return null;
		list = this.getList("from Membership where 1=1 and (type = 0 or type is null) and parent is null "+sb.toString()+"  order by createDate desc  ",null,(page-1)*limit,limit);
		count = this.getList("from Membership where 1=1 and (type = 0 or type is null) and parent is null "+sb.toString()+" ",null);
		if(!StringUtil.isNull(s.get("type"))&&!StringUtil.isNull(s.get("phone"))){
			list = this.getList("from Membership where phone like '%"+s.get("phone")+"%' or name like '%"+s.get("phone")+"%' or parent.phone like '%"+s.get("phone")+"%'  order by createDate desc ",null,(page-1)*limit,limit);
			count = this.getList("from Membership where phone like '%"+s.get("phone")+"%' or name like '%"+s.get("phone")+"%' or parent.phone like '%"+s.get("phone")+"%'  order by createDate desc",null);
		}
		if(!StringUtil.isNull(s.get("type"))){
			list = this.getList("from Membership where 1=1  order by createDate desc ",null,(page-1)*limit,limit);
			count = this.getList("from Membership where 1=1 order by createDate desc",null);
		}
		for(Membership f:list){
			JSONObject obj = new JSONObject();
			obj.put("id",f.getId());
			obj.put("name",f.getName());
			obj.put("phone",f.getPhone());
			obj.put("status","1".equals(f.getStatus())?"启用":"禁用");
			obj.put("carNo",f.getCardNo());
			obj.put("createEmp",f.getCreateEmployee()!=null?f.getCreateEmployee().getName():"");
			obj.put("createTime", DateUtil.format(f.getCreateDate(),"yyyy-MM-dd HH:mm:ss"));
			array.add(obj);
		}
		JSONObject object = new JSONObject();
		object.put("code",0);
		object.put("msg","");
		object.put("count",count.size());
		object.put("data",array);
		return object;
	}

	public List<Membership> getListByIdPwd(String phone){
		List<Membership> list = new ArrayList<Membership>();
		list = this.getList("from Membership where phone like '%"+phone+"%' or name like '%"+phone+"%' or parent.phone like '%"+phone+"%'  order by createDate desc",null);
		return list;
	}

	/**
	 * 根据类型获取职务列表
	 * @param status
	 * @return
	 */
	public List<Membership> getListByType(Byte status){
		String hql = "from Membership where  1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(status!=null){
			hql += " and status = ? ";
			params.add(status);
		}
		return this.getList(hql,params.toArray());
	}

	/**
	 * 根据类型获取职务列表
	 * @param parent
	 * @return
	 */
	public List<Membership> getListByParent(Integer parent){
		String hql = "from Membership where  1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(parent!=null){
			hql += " and parent.id = ? ";
			params.add(parent);
		}
		return this.getList(hql,params.toArray());
	}

	/**
	 * 根据类型获取职务列表
	 * @param parent
	 * @return
	 */
	public List<Membership> getListByParentAndCType(Integer parent,String cType){
		String hql = "from Membership where  1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(parent!=null){
			hql += " and parent.id = ? ";
			params.add(parent);
		}
		if(cType!=null){
			hql += " and cType = ? ";
			params.add(cType);
		}
		return this.getList(hql,params.toArray());
	}

	/**														
	 * 增加对象												
	 * @param o	需要增加的对象									
	 * @return	返回增加的对象								
	 */														
	public Object save(Membership o) {
		return super.save(o);								
	}														
															
	/**														
	 * 删除对象												
	 * @param o 需要删除的对象									
	 * @return	返回 true或false 是否删除成功					
	 */														
	public boolean delete(Membership o) {
		return super.delete(o);								
	}			
	
	/**														
	 * 更新对象												
	 * @param o 需要更新的对象									
	 * @return	返回更新的对象								
	 */														
	public boolean update(Membership o) {
		return super.update(o);								
	}		
	
															
}