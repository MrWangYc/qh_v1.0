package com.qh.service.base;

import com.alibaba.fastjson.JSON;
import com.qh.dao.base.SysCodeDao;
import com.qh.model.base.SysCode;
import com.qh.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
*	系统基础编码（数据字典）
* @创建人 			王云川
* @创建日期 		2017-09-25
* @修改人 			王云川
* @修改日期 		2017-09-25
* @版本号 			1.0.0
* @版权所有 		XX科技
*/

@Service
public class SysCodeService extends BaseService {
	@Autowired
	private SysCodeDao baseDao;

	public JSON getJsonArray(Integer id,Map<String, String> s){
		return baseDao.getJsonList(id,s);
	}

	/**
	 * 根据父级id查询列表
	 * @param id
	 * @return
	 */
	public List<SysCode> getListByParentId(Integer id){
		return baseDao.getListByParentId(id);
	}

	/**
	 * 根据字典状态获取数据字典
	 * @param status	为空：所有状态，0：停用的，1：启用的
	 * @return
	 */
	public List<SysCode> getListByStatus(Byte status){
		return baseDao.getListByStatus(status);
	}
	
	/**
	 * 保存
	 * @param o	需要增加的对象
	 * @return	返回增加的对象
	 */
	public SysCode save(SysCode o){
		baseDao.save(o);
		return o;
	}
	
	/**
	 * 修改
	 * @param o 需要更新的对象
	 * @return	返回更新的对象
	 */
	public SysCode update(SysCode o){
		baseDao.update(o);
		return o;
	}
	
	
	/**
	 * 删除
	 * @param o 需要更新的对象			
	 * @return	返回更新的对象
	 */
	public boolean delete(SysCode o){
		return baseDao.delete(o);
	}



	/**
	 * 获得所有数据字典
	 *
	 * @return
	 */
	public List<SysCode> getAllSysCodeList(){
		return baseDao.getList("from SysCode",null);
	}


	public SysCodeDao getBaseDao() {
		return baseDao;
	}


}