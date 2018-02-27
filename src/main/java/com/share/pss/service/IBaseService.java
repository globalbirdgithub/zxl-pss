package com.share.pss.service;

import java.io.Serializable;
import java.util.List;

import com.share.pss.query.BaseQuery;
import com.share.pss.query.PageList;

/**
 * @author MrZhang
 * @date 2017年10月31日 下午12:00:26
 * @version V1.0 业务层公共接口-有子类，所以可通过继承传入Class
 */
public interface IBaseService<T> {

	void saveOrUpdate(T t);

	void delete(Serializable id);

	T get(Serializable id);

	List<T> getAll();

	// 分页查询
	PageList findByQuery(BaseQuery baseQuery);
}
