package com.share.pss.dao;

import java.io.Serializable;
import java.util.List;

import com.share.pss.query.BaseQuery;
import com.share.pss.query.PageList;

/**
 * @author MrZhang
 * @date 2017年10月31日 上午2:45:38
 * @version V1.0 持久层公共接口-没有子类，所以需要通过方法传入Class
 */
public interface IBaseDao<T> {
	// Hibernate会自动判断保存或更新
	void saveOrUpdate(T t);

	// 作为公共接口，数据库中许多表，通过Class指定表
	void delete(Class<T> entityClass, Serializable id);

	// 通过Class指定表
	T get(Class<T> entityClass, Serializable id);

	// 通过Class指定表
	List<T> getAll(Class<T> entityClass);
	
	//分页查询
	PageList findByQuery(BaseQuery baseQuery);
}
