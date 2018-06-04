package com.qh.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @创建人 王云川
 * @创建日期 2017-09-19
 * @修改人 王云川
 * @修改日期 2017-09-09
 * @版本号 1.0.1
 * @版权所有 XX科技
 */
@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW )
public class BaseDao {
	public static Log log = LogFactory.getLog(BaseDao.class);

	@Autowired
	public HibernateTemplate hibernateTemplate;

	@Autowired
	public JdbcTemplate jdbcTemplate;



	/**
	 * 清除缓存
	 */
	public void clear() {
		hibernateTemplate.clear();
	}
	
	public void flush() {
		hibernateTemplate.flush();
	}
	

	/**
	 * 删除对象
	 * @param o 要删除的堆箱
	 * @return
	 */
	public boolean delete(Object o) {
		hibernateTemplate.delete(o);
		return true;
	}

	/**
	 * 批量删除对象
	 * @param list    要批量删除的对象集合
	 * @return
	 */
	public boolean deleteAll(List<?> list) {
		hibernateTemplate.deleteAll(list);
		return true;
	}

	/**
	 * 执行HQL
	 * @param hql		语句
	 * @param params	条件
	 * @return
	 */
	public int excuteHql(final String hql, final Object... params) {
		return (Integer) hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query q = session.createQuery(hql);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						Object param = params[i];
						if (param.getClass().isArray()) {
							q.setParameterList("ids", (Object[]) param);
						} else if (param instanceof Collection) {
							q.setParameterList("ids", (Collection) param);
						} else {
							q.setParameter(i, params[i]);
						}
					}
				}
				return q.executeUpdate();
			}
		});
	}

	/**
	 * 执行SQL
	 * @param sql	sql语句
	 * @param params	条件
	 * @return
	 */
	public int excuteSql(final String sql, final Object... params) {
		return (Integer) hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query q = session.createSQLQuery(sql);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						Object param = params[i];
						if (param.getClass().isArray()) {
							q.setParameterList("ids", (Object[]) param);
						} else if (param instanceof Collection) {
							q.setParameterList("ids", (Collection) param);
						} else {
							q.setParameter(i, params[i]);
						}
					}
				}
				return q.executeUpdate();
			}
		});
	}


	/**
	 * 根据类名和主键ID获取对象
	 * @param entityClass	类型
	 * @param id			主键
	 * @return
	 */
	public <T> T get(Class<T> entityClass, Serializable id) {
		return hibernateTemplate.get(entityClass, id);
	}

	/**
	 * 查询单个浮点型数据
	 * @param hql	hql语句
	 * @return
	 */
	public float getFloat(final String hql,Object[] params) {
		List<Float> list = getList(hql+" limit 1",params);
		if(list!=null && list.size()>0 && list.get(0) !=null){
			Float num = (Float) list.get(0);
			return num.intValue();
		}
		return 0;
	}

	/**
	 * 查询单个整型数据
	 * @param hql		hql语句
	 * @param params	查询条件参数
	 * @return
	 */
	public int getInteger(final String hql,Object[] params){
		List<Integer> list = getList(hql+" limit 1",params);
		if(list!=null && list.size()>0 && list.get(0) !=null){
			Number num = (Number) list.get(0);
			return num.intValue();
		}

		return 0;
	}

	/**
	 * 查询单个浮点型数据
	 * @param sql	sql语句
	 * @return
	 */
	public float getFloatBySql(final String sql,Object[] params) {
		List<Object[]> list=getListBySql(sql+" limit 1",params);
		float allNum=0f;
		if(list.size()!=0){
			Object o=list.get(0);
			if(o!=null && StringUtil.isFloat(o.toString()))
				allNum = Float.parseFloat(o.toString());
		}
		return allNum;
	}

	/**
	 * 查询单个整形数据
	 * @param sql	sql语句
	 * @param params	条件
	 * @return
	 */
	public Integer getIntegerBySql(final String sql,Object[] params) {
		List<Object[]> list=getListBySql(sql+" limit 1",params);
		Integer allNum=0;
		if(list.size()!=0){
			Object o=list.get(0);
			if(o!=null && StringUtil.isInteger(o.toString()))
				allNum = Integer.parseInt(o.toString());
		}
		return allNum;
	}
	/**
	 * 按照HQL语句查询结果
	 * @param hql		hql语句
	 * @param params	条件
	 * @return
	 */
	public List getList(final String hql,final Object[] params) {
		List allList = (List) hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query q = session.createQuery(hql);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						Object param = params[i];
						if (param.getClass().isArray()) {
							q.setParameterList("ids", (Object[]) param);
						} else if (param instanceof Collection) {
							q.setParameterList("ids", (Collection) param);
						} else {
							q.setParameter(i, params[i]);
						}
					}
				}
				return q.list();
			}
		});
		return allList;
	}

	/**
	 * HQL分页查询list
	 * @param hql
	 * @param firstSize 起始数
	 * @param maxSize	最大结果数量
	 * @return
	 */
	public List getList(final String hql,final Object params[],final int firstSize, final int maxSize) {
		List allList = (List) hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query q = session.createQuery(hql);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						q.setParameter(i, params[i]);
					}
				}
				if (maxSize > 0)
					q.setMaxResults(maxSize);
				if (firstSize > 0)
					q.setFirstResult(firstSize);
				return q.list();
			}
		});
		return allList;
	}
	/**
	 * 通过sql获取统计条数的sql
	 *
	 * @param sql sql
	 * @return
	 */
	private String getCountBySql(final String sql) {
		String countSql = sql.toLowerCase();
		int fromIndex = countSql.indexOf("from ");
		if (fromIndex != -1)
			countSql = "select count(*) " + countSql.substring(fromIndex);
		int index = countSql.indexOf("order by");
		if (index != -1)
			countSql = countSql.substring(0, index);
		return countSql;
	}

	/**
	 * 通过hql获取统计条数的hql
	 *
	 * @param hql hql
	 * @return
	 */
	private String getCountByHql(final String hql) {
		String countHql = hql;
		int fromIndex = countHql.indexOf("from ");
		if (fromIndex != -1)
			countHql = "select count(*) " + countHql.substring(fromIndex);
		int index = countHql.indexOf("order by");
		if (index != -1)
			countHql = countHql.substring(0, index);
		int fetchIndex = countHql.indexOf(" fetch ");
		while (fetchIndex != -1) {
			countHql = countHql.substring(0, fetchIndex) + countHql.substring(fetchIndex + 6);
			fetchIndex = countHql.indexOf(" fetch ");
		}
		return countHql;
	}

	/**
	 * 按照Sql语句查询结果  返回二维数组
	 * @param sql		sql语句
	 * @param params	条件
	 * @return
	 */
	public List getListBySql(final String sql, final Object[] params) {
		List pList = (List) hibernateTemplate.execute(new HibernateCallback() {
			public List doInHibernate(Session session) throws HibernateException {
				Query q = session.createSQLQuery(sql);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						q.setParameter(i, params[i]);
					}
				}
				return q.list();
			}
		});
		return pList;
	}

	/**
	 * 按照Sql语句查询结果 返回对象Map
	 * @param sql
	 * @param params
	 * @return
	 */
	public Map getObjectMapBySql(final String sql, final Object[] params) {
		return hibernateTemplate.execute(new HibernateCallback<Map<String, Object>>() {
			@Override
			public Map<String, Object> doInHibernate(Session session) throws HibernateException {
				Query q = session.createSQLQuery(sql);
				if (params != null) {
					for (int i = 0, len = params.length; i < len; i++) {
						q.setParameter(i, params[i]);
					}
				}
				q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				q.setMaxResults(1);
				List list = q.list();
				if (list.size() > 0)
					return (Map<String, Object>) list.iterator().next();
				else
					return null;
			}
		});
	}

	/**
	 * 用sql语句查询map结构的集合
	 * @param sql        sql语句
	 * @param params    条件
	 * @return
	 */
	public List getListMapBySql(final String sql, final Object[] params){
		List pList = (List) hibernateTemplate.execute(new HibernateCallback() {
			public List doInHibernate(Session session) throws HibernateException {
				Query q = session.createSQLQuery(sql);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						q.setParameter(i, params[i]);
					}
				}
				q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				return q.list();
			}
		});
		return pList;
	}


	/**
	 * 分页查询返回JSONObject
	 *
	 * @param sql      查询语句
	 * @param curPage  当前页数
	 * @param pageSize 每页数量
	 * @param params   查询参数
	 * @param loadType 查询方式，0：只查询列表，1：只统计，2：查询统计
	 * @return
	 */
	public JSONObject getJSONListBySql(String sql, int curPage, int pageSize, final Object[] params, int loadType) {
		return getJSONListBySql(sql, getCountBySql(sql), curPage,pageSize,params,loadType);
	}


	/**
	 * 分页查询返回JSONObject
	 * @param sql		查询语句
	 * @param countSql	统计语句
	 * @param curPage	当前页数
	 * @param pageSize	每页数量
	 * @param params	查询参数
	 * @param loadType 查询方式，0：只查询列表，1：只统计，2：查询统计
	 * @return
	 */
	public JSONObject getJSONListBySql(String sql, String countSql, int curPage, int pageSize, final Object[] params, int loadType){
		JSONObject result=new JSONObject();
		if(loadType == 2 || loadType == 1){
			result.put("total",getIntegerBySql(countSql, params));
		}else{
			result.put("total",0);
		}
		if(loadType == 2 || loadType == 0){
			result.put("rows", JSON.parse(getListMapBySql(sql, curPage, pageSize, params).toString()));


		}else{
			result.put("rows",new JSONArray().toArray());
		}

		return result;
	}


	/**
	 *  用sql查询map结构的分页集合
	 * @param sql			sql语句
	 * @param curPage		当前页数
	 * @param pageSize		每页数量
	 * @param params		条件
	 * @return
	 */
	public List getListMapBySql(final String sql,final int curPage, final int pageSize,final Object[] params){
		List pList = (List) hibernateTemplate.execute(new HibernateCallback() {
			public List doInHibernate(Session session) throws HibernateException {
				Query q = session.createSQLQuery(sql);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						q.setParameter(i, params[i]);
					}
				}
				if(pageSize >=0){
					q.setMaxResults(pageSize);
					q.setFirstResult(pageSize * (curPage - 1) );
				}
				q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				return q.list();
			}
		});
		return pList;
	}


	/**
	 * 按照主键查询对象
	 * @param clazz		类
	 * @param pkId		主键
	 * @return
	 */
	public Object getObject(final Class clazz, final Object pkId) {
		Object obj = hibernateTemplate.execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria q = session.createCriteria(clazz);
				q.add(Restrictions.eq("id", pkId));
				return q.uniqueResult();
			}
		});
		return obj;
	}


	/**
	 * 按照 hql 查询一个对象，带参数
	 * @param hql		hql语句
	 * @param params	条件
	 * @return
	 */
	public Object getObject(final String hql,final Object[] params) {
		Object obj = hibernateTemplate.execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query q = session.createQuery(hql);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						q.setParameter(i, params[i]);
					}
				}
				q.setMaxResults(1);
				List list = q.list();
				if (list.size() > 0)
					return list.iterator().next();
				else
					return null;
			}
		});
		return obj;
	}










	/**
	 * @param proxy
	 */
	public void initProxy(final Object proxy) {
		try {

			hibernateTemplate.execute(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException {
					session.update(proxy);
					Hibernate.initialize(proxy);
					return null;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据类名和主键ID获取对象
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public <T> T load(Class<T> entityClass, Serializable id) {
		return hibernateTemplate.load(entityClass, id);
	}

	/**
	 * * 更新对象(更新之前不清除缓存)
	 *
	 * @param o
	 * @return
	 */
	public void reUpdate(Object o) {
		hibernateTemplate.update(o);
	}

	/**
	 * 保存对象
	 *
	 * @param o
	 * @return
	 */
	public Object save(Object o) {
		if(o != null){
			hibernateTemplate.save(o);
		}
		return o;
	}


	/**
	 * 保存或更新对象
	 *
	 * @param o
	 * @return
	 */
	public boolean saveOrUpdate(Object o) {
		boolean success = true;
		try {
			hibernateTemplate.saveOrUpdate(o);
		} catch (RuntimeException e) {
			success = false;
			log.error(e);
		}
		return success;
	}

	/**
	 * 更新对象
	 *
	 * @param o
	 * @return
	 */
	public boolean update(Object o) {
		boolean success = true;
		hibernateTemplate.clear();
		hibernateTemplate.update(o);
		hibernateTemplate.flush();
		return success;
	}



	/**
	 * 批量更新对象
	 * @param list	要更新的对象集合
	 * @return
	 */

	public boolean updateList(final Collection list) {
		boolean success = true;
		success = (Boolean) hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException{
				boolean flag = true;
				try {
					Object obj = null;
					int i = 0;
					for (Iterator it = list.iterator(); it.hasNext();) {
						obj = it.next();
						if(obj != null)
							session.update(obj);
						obj = null;
						if ((i + 1) % 50 == 0) {
							session.flush();
							session.clear();
						}
						i++;
					}
				} catch (Exception e) {
					log.error(e);
					e.printStackTrace();
					flag = false;
				}
				return flag;
			}
		});
		return success;
	}

	/**
	 * 	批量保存或者更新对象
	 * @param list	对象集合
	 * @return
	 */

	public boolean saveOrUpdateList(final Collection list) {
		boolean success = true;
		success = (Boolean) hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				boolean flag = true;
				try {
					Object obj = null;
					int i = 0;
					for (Iterator it = list.iterator(); it.hasNext();) {
						obj = it.next();
						if(obj != null)
							session.saveOrUpdate(obj);
						obj = null;
						if ((i + 1) % 50 == 0) {
							session.flush();
							session.clear();
						}
						i++;
					}
				} catch (Exception e) {
					log.error(e);
					e.printStackTrace();
					flag = false;
				}
				return flag;
			}
		});
		return success;
	}

	/**
	 * 批量保存对象
	 * @param list	对象集合
	 * @return
	 */
	public boolean saveList(final Collection list) {
		boolean success = true;
		success = (Boolean) hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException{
				boolean flag = true;
				try {
					Object obj = null;
					int i = 0;
					for (Iterator it = list.iterator(); it.hasNext();) {
						obj = it.next();
						if(obj != null)
							session.save(obj);
						obj = null;
						if ((i + 1) % 100 == 0) {
							session.flush();
							session.clear();
						}
						i++;
					}
				} catch (Exception e) {
					log.error(e);
					e.printStackTrace();
					flag = false;
				}
				return flag;
			}
		});
		return success;
	}
}
