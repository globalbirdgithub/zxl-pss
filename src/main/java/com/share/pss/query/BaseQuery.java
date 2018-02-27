package com.share.pss.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @author MrZhang
 * @date 2017年12月13日 上午2:35:44
 * @version V1.0 封装分页查询所需条件
 */
public abstract class BaseQuery {
	//分页查询参数currentPage/pageSize
	private int currentPage = 1;// 接收前台参数：当前页，默认1
	private int pageSize = 10;// 接收前台参数：每页条数，默认10
	//分页查询语句hql
	private StringBuilder countHql;// 总条数sql查询语句
	private StringBuilder limitHql;// 分页sql查询语句
	//分页/高级查询参数集合
	private List<Object> paramList;// sql参数集合（底层数组）
	
	/**获取类名创建hql语句
	 * @param className 
	 */
	public BaseQuery(String className) {
		this.countHql = new StringBuilder("select count(o) from "+className+" o");
		this.limitHql = new StringBuilder("select o from "+className+" o");
		this.paramList = new ArrayList<>();
	}
	//父类与子类之间通过抽象方法传递参数
	protected abstract void addCondition();
	
	/**子类通过调用此方法来传递高级查询参数
	 * @param hql 拼接sql
	 * @param object 查询条件
	 * 2017年12月13日下午6:13:23
	 */
	protected void addCondition(String hql,Object...object){
		if(paramList.size()==0){//如果没参数则拼接where
			countHql.append(" where ").append(hql);
			limitHql.append(" where ").append(hql);
		}else{//有参数则拼接and
			countHql.append(" and ").append(hql);
			limitHql.append(" and ").append(hql);
		}
		paramList.addAll(Arrays.asList(object));//将参数添加到集合List
	};
	//在调用getCountHql/getLimitHql/getParamList的时候防止重复调用addCondition
	private boolean flag = false;
	private void builderAddCondition(){
		if(!flag){
			addCondition();
			flag = true;
		}
	}
	//以下setter/getter提供给dao使用
	public String getCountHql() {
		builderAddCondition();//获取hql拼接查询条件
		return countHql.toString();
	}
	public String getLimitHql() {
		builderAddCondition();//获取hql拼接查询条件
		return limitHql.toString();
	}
	public List<Object> getParamList() {
		builderAddCondition();//获取hql拼接查询条件
		return paramList;
	}
	//以下setter/getter提供给Struts2使用
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
