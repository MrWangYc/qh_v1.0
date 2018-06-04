package com.qh.service.user;

import com.alibaba.fastjson.JSON;
import com.qh.dao.base.PositionDao;
import com.qh.dao.user.CompanyDao;
import com.qh.model.user.Company;
import com.qh.model.user.Position;
import com.qh.service.BaseService;
import com.util.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * @版权所有 XX科技
 */
@Service
public class CompanyService extends BaseService {
	@Autowired
	private CompanyDao baseDao;// 功能模块DAO

	public JSON getJsonArray(Map<String, String> s){
		return baseDao.getJsonList(s);
	}


	public List<Company> getListByStatus(String status){
		return baseDao.getListByStatus(status);
	}

	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 */
	public Company getObject(Integer id){
		return (Company) baseDao.getObject(Company.class,id);
	}

	/**
	 * 添加系统功能PositionPosition
	 * @param object 对象
	 * @return
	 */
	public Company save(Company object) {
		baseDao.save(object);
		return object;
	}

	/**
	 * 修改系统功能
	 * @param object 对象
	 * @return
	 */
	public Company update(Company object) {
//		Company o=new Company();
//		BeanCopyUtil.copy(o,baseDao.getObject(Company.class, object.getId()));
		baseDao.update(object);
		return object;
	}

	/**
	 * 删除系统功能
	 * @param object 需要删除的新闻公告
	 */
	public void delete(Company object) {
		baseDao.delete(object);
	}

}