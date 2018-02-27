package com.share.pss.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.share.pss.dao.IBaseDao;
import com.share.pss.query.BaseQuery;
import com.share.pss.query.PageList;

/**
 * @author MrZhang 
 * @date 2017年10月31日 上午2:50:52
 * @version V1.0 持久层公共接口实现类
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T>{
	//注意需要注入SessionFactory
	@Override
	public void saveOrUpdate(T t) {
		getHibernateTemplate().saveOrUpdate(t);//T executeWithNativeSession(HibernateCallback<T> action)
	}
	@Override
	public void delete(Class<T> entityClass, Serializable id) {
		T t = get(entityClass, id);
		if(t!=null){
			getHibernateTemplate().delete(t);//T executeWithNativeSession(HibernateCallback<T> action)
		}
	}
	@Override
	public T get(Class<T> entityClass, Serializable id) {
		T t = getHibernateTemplate().get(entityClass, id);//T executeWithNativeSession(HibernateCallback<T> action)
		return t;
	}
	@Override
	public List<T> getAll(Class<T> entityClass) {
		List<T> list = getHibernateTemplate().loadAll(entityClass);//T executeWithNativeSession(HibernateCallback<T> action)
		return list;
	}
	//分页查询-模仿上面操作
	@SuppressWarnings("rawtypes")
	@Override
	public PageList findByQuery(final BaseQuery baseQuery) {/*匿名内部类内部必须加上final才能使用局部变量*/
		Long count = getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Long>(){//T executeWithNativeSession(HibernateCallback<T> action)
			@Override
			public Long doInHibernate(Session session) throws HibernateException, SQLException {
				Query countQuery = session.createQuery(baseQuery.getCountHql());
				List paramList = baseQuery.getParamList();
				for (int i = 0; i < paramList.size(); i++) {
					countQuery.setParameter(i, paramList.get(i));
				}
				Long count = (Long) countQuery.uniqueResult();
				return count;
			}
		});
		//如果查询总数为0，则返回空。
		if(count.intValue()==0){
			return new PageList();
		}
		
		final PageList pageList = new PageList(baseQuery.getCurrentPage(), baseQuery.getPageSize(), count.intValue());
		
		List list = getHibernateTemplate().execute(new HibernateCallback<List>() {
			@Override
			public List doInHibernate(Session session) throws HibernateException, SQLException {
				Query limitQuery = session.createQuery(baseQuery.getLimitHql());
				List paramList = baseQuery.getParamList();
				//高级查询条件
				for (int i = 0; i < paramList.size(); i++) {
					limitQuery.setParameter(i, paramList.get(i));
				}
				//分页查询条件
				int startIndex = (pageList.getCurrentPage()-1)*pageList.getPageSize();
				int pageSize = pageList.getPageSize();
				limitQuery.setFirstResult(startIndex);
				limitQuery.setMaxResults(pageSize);
				List list = limitQuery.list();
				return list;
			}
		});
		pageList.setRows(list);
		return pageList;
	}
}
