package com.share.pss.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.share.pss.dao.IBaseDao;
import com.share.pss.query.BaseQuery;
import com.share.pss.query.PageList;
import com.share.pss.service.IBaseService;

/**
 * @author MrZhang
 * @date 2017年10月31日 下午12:07:58
 * @version V1.0 业务层公共接口实现类:1.防止抽象类被实例化；2.强制子类重载抽象方法； 
 * 关于getClass()方法：--此时，由于 abstract抽象类不能直接被创建实例，所以getClass()返回的必定是子类实例；
 * --当子类实例被创建的同时会调用父类构造方法；new BaseServiceImpl(){}想当于实现BaseServiceImpl这个类。
 */
public abstract class BaseServiceImpl<T> implements IBaseService<T> {
	//通过无参构造方法获取之类设置的泛型Employee
	private Class<T> entityClass;
	public BaseServiceImpl(){
		//返回此 Object 的运行时类（调用底层C++）-EmployeeServiceImpl
		Class clazz = getClass();
		//返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type（标识接口）。-BaseServiceImpl<Employee>
		Type type = clazz.getGenericSuperclass();
		//ParameterizedType参数化类型是Type的子接口；判断是否属于参数化类型并强转；-BaseServiceImpl<Employee>
		if(type instanceof ParameterizedType){
			ParameterizedType parameterizedType = (ParameterizedType)type;
			//返回表示此类型实际类型参数的 Type 对象的数组。 -[Ljava.lang.reflect.Type;@297425c3
			Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
			//Class是Type的实现类，强转 -entityClass
			this.entityClass = (Class)actualTypeArguments[0];
		}
	}
	//由子类注入IBaseDao
	private IBaseDao<T> baseDao;
	public void setBaseDao(IBaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}
	@Override
	public void saveOrUpdate(T t) {
		baseDao.saveOrUpdate(t);
	}
	@Override
	public void delete(Serializable id) {
		baseDao.delete(entityClass, id);
	}
	@Override
	public T get(Serializable id) {
		T t = baseDao.get(entityClass, id);
		return t;
	}

	@Override
	public List<T> getAll() {
		List<T> list = baseDao.getAll(entityClass);
		return list;
	}
	//分页查询
	@Override
	public PageList findByQuery(BaseQuery baseQuery) {
		return baseDao.findByQuery(baseQuery);
	}

}
