package com.qh.service.user;

import com.alibaba.fastjson.JSON;
import com.qh.dao.user.MembershipDao;
import com.qh.model.user.MemCate;
import com.qh.model.user.Membership;
import com.qh.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 系统功能模块配置，一级菜单、二级菜单、子功能权限配置
 * 
 * @创建人 王云川
 * @创建日期 2017-03-22
 * @修改人 王云川
 * @修改日期 2017-03-22
 * @版本号 1.0.0
 * @版权所有
 */
@Service
public class MembershipService extends BaseService {
	@Autowired
	private MembershipDao baseDao;// 功能模块DAO

	public JSON getJsonArray(Map<String, String> s, Integer page, Integer limit) throws IOException {
		return baseDao.getJsonList(s,page,limit);
	}

	/**
	 * 根据状态获取对应的列表
	 * @param status
	 * @return
	 */
	public List<Membership> getListByType(Byte status){
		return baseDao.getListByType(status);
	}

	public List<Membership> getListByParent(Integer parent,String cType){
		return baseDao.getListByParentAndCType(parent,cType);
	}

	public List<Membership> getListByPhone(String phone){
		return baseDao.getListByIdPwd(phone);
	}

	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 */
	public Membership getObject(Integer id){
		return (Membership) baseDao.getObject(Membership.class,id);
	}

	/**
	 * 添加系统功能MembershipMembership
	 * @param object 对象
	 * @return
	 */
	public Membership save(Membership object) {
		baseDao.save(object);
		return object;
	}




	/**
	 * 修改系统功能
	 * @param object 对象
	 * @return
	 */
	public Membership update(Membership object) {
		/*Membership o=new Membership();
		BeanCopyUtil.copy(o,baseDao.getObject(Membership.class, object.getId()));*/
		baseDao.update(object);
		return object;
	}

	/**
	 * 删除系统功能
	 * @param object 需要删除的新闻公告
	 */
	public void delete(Membership object) {
		baseDao.delete(object);
		List<Membership> list = baseDao.getListByParent(object.getId());
		for(Membership m:list){
			baseDao.delete(m);
		}
	}

}