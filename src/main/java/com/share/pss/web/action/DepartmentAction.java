package com.share.pss.web.action;

import com.share.pss.domain.Department;
import com.share.pss.query.DepartmentQuery;
import com.share.pss.query.PageList;
import com.share.pss.service.IDepartmentService;
/**
 * @author MrZhang 
 * @date 2017年12月21日 下午2:29:48
 * @version V1.0 部门表现层
 */
public class DepartmentAction extends CRUDAction<Department>{
	private static final long serialVersionUID = 1L;
	//Spring管理
	private IDepartmentService departmentService;
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	//Struts2管理(后台向前台)
	private PageList pageList;
	public PageList getPageList() {
		return pageList;
	}
	//Struts2管理(后台向前台提供)
	private DepartmentQuery departmentQuery = new DepartmentQuery();
	public DepartmentQuery getDepartmentQuery() {
		return departmentQuery;
	}
	//值栈
	private Department department;
	//=====================Action方法======================
	@Override
	protected void list() {
		this.pageList = departmentService.findByQuery(departmentQuery);
	}

	@Override
	protected void inputt() {
		/*留空*/
	}

	@Override
	protected void savee() {
		departmentService.saveOrUpdate(department);
	}

	@Override
	protected void deletee() {
		if(id!=null){
			departmentService.delete(id);
		}
	}
	//==================ModelDriven/Preparable=====================
	@Override
	protected void preparee() {
	}

	@Override
	protected Department getModell() {
		return department;
	}

	@Override
	protected void prepareInputt() {
		if(id!=null){
			department = departmentService.get(id);
		}
	}

	@Override
	protected void prepareSavee() {
		if(id==null){
			department = new Department();
		}else{
			department = departmentService.get(id);
		}
	}

	@Override
	protected void prepareDeletee() {
		
	}
	
}
