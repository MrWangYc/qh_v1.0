package com.qh.dao.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qh.dao.BaseDao;
import com.qh.model.user.Consumption;
import com.qh.model.user.MemCate;
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
public class ConsumptionDao extends BaseDao {
	/**
	 * 获取数据并显示到界面上去,将无法直接看懂的参数直接转化为对应的数据
	 * @param s
	 * @return
	 */
	public JSON getJsonList(Map<String, String> s, Integer page, Integer limit)throws IOException {
		JSONArray array = new JSONArray();
		List<Consumption> list = new ArrayList<Consumption>();
		List<Consumption> count = new ArrayList<Consumption>();
		StringBuffer sb = new StringBuffer();
		if(!StringUtil.isNull(s.get("name")))
			sb.append(" and name like '%"+ URLDecoder.decode(s.get("name"),"UTF-8")+"%' ");
		if(!StringUtil.isNull(s.get("phone")))
			sb.append(" and Phone like '%"+ URLDecoder.decode(s.get("phone"),"UTF-8")+"%' ");
		if("1".equals(CtxUtil.getLoginUser().getEmployee().getPosition().getDataAuth())|| Constants.ADMIN_PHONE.equals(CtxUtil.getLoginUser().getEmployee().getPhone())){

		}else if("2".equals(CtxUtil.getLoginUser().getEmployee().getPosition().getDataAuth())){
			sb.append(" and createEmp.company.id = "+CtxUtil.getLoginUser().getEmployee().getCompany().getId());
		}else if("3".equals(CtxUtil.getLoginUser().getEmployee().getPosition().getDataAuth())){
			sb.append(" and createEmp.id = "+CtxUtil.getLoginUser().getEmployee().getId());
		}else
			return null;
		list = this.getList("from Consumption where 1=1 "+sb.toString()+"  ",null,(page-1)*limit,limit);
		count = this.getList("from Consumption where 1=1 "+sb.toString()+" ",null);
		for(Consumption f:list){
			JSONObject obj = new JSONObject();
			obj.put("id",f.getId());
			obj.put("mName",f.getMemCate().getMembership().getName());
			obj.put("mPhone",f.getMemCate().getMembership().getPhone());
			obj.put("cName",f.getMemCate().getCategory().getName());
			obj.put("cType","1".equals(f.getMemCate().getCategory().getType())?"次数卡":("2".equals(f.getMemCate().getCategory())?"月卡":("3".equals(f.getMemCate().getCategory())?"季卡":"年卡")));
			obj.put("cDate", DateUtil.format(f.getcDate(),"yyyy-MM-dd HH:mm:ss"));
			array.add(obj);
		}
		JSONObject object = new JSONObject();
		object.put("code",0);
		object.put("msg","");
		object.put("count",count.size());
		object.put("data",array);
		return object;
	}

	/**														
	 * 增加对象												
	 * @param o	需要增加的对象									
	 * @return	返回增加的对象								
	 */														
	public Object save(Consumption o) {
		return super.save(o);								
	}														
															
	/**														
	 * 删除对象												
	 * @param o 需要删除的对象									
	 * @return	返回 true或false 是否删除成功					
	 */														
	public boolean delete(Consumption o) {
		return super.delete(o);								
	}			
	
	/**														
	 * 更新对象												
	 * @param o 需要更新的对象									
	 * @return	返回更新的对象								
	 */														
	public boolean update(Consumption o) {
		return super.update(o);								
	}
															
}