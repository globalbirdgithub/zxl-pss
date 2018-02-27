package com.share.pss.web.action;
import com.share.pss.domain.Department;
import com.share.pss.domain.Employee;
import com.share.pss.query.EmployeeQuery;
import com.share.pss.query.PageList;
import com.share.pss.service.IDepartmentService;
import com.share.pss.service.IEmployeeService;
/**
 * @author MrZhang
 * @date 2017年11月1日 下午11:32:44
 * @version V1.0 表现层Action 访问Action的时候默认Action在栈顶，ModelDriven对应的拦截器检测到Employee有值的时候将其压栈到栈顶
 * 													  Preparable对应的拦截器检测到要执行Action中的方法的时候就执行prepare()
 */
public class EmployeeAction extends CRUDAction<Employee>{
	private static final long serialVersionUID = 1L;
	//Spring管理
	private IEmployeeService employeeService;
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	private IDepartmentService departmentService;
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	//Struts2管理 通过值栈(List/Map)向前台提供数据，
	//List栈需要：属性+getter；Map栈需要：ActionContext.getContext.put(key,value)
	private PageList pageList;
	public PageList getPageList() {
		return pageList;
	}
	//Struts2管理 需要setter、getter
	private EmployeeQuery employeeQuery = new EmployeeQuery();
	public EmployeeQuery getEmployeeQuery() {
		return employeeQuery;
	}
	//Struts2管理 用于接收和回显前台数据，需要它在栈顶时才放到栈顶
	private Employee employee;
	
	//====================================Action方法=========================================
	//获取所有
	@Override
	protected void list() {
		this.pageList = employeeService.findByQuery(employeeQuery);
		putContext("allDepts", departmentService.getAll());
	}
	@Override
	protected void inputt() {
		/*留空*/
		putContext("allDepts", departmentService.getAll());
	}
	@Override
	protected void savee() {
		Department department = employee.getDepartment();
		if(department!=null && department.getId()==-1L){
			employee.setDepartment(null);
		}
		employeeService.saveOrUpdate(employee);
	}
	@Override
	protected void deletee(){
		if(id!=null){
			employeeService.delete(id);
		}
	}
	//==================================实现ModelDriven和Prepareable接口解决属性丢失问题========================
	@Override
	protected void preparee() {
	}
	@Override
	protected Employee getModell() {
		return employee;
	}
	//=========================prepare拦截器对应的类PrepareInterceptor检测方法前缀prefix='prepare'通过反射调用=======
	@Override
	protected void prepareInputt(){
		if(id!=null){
			employee = employeeService.get(id);//修改需要回显否则不需要(这时会压栈)
		}
	}
	@Override
	protected void prepareSavee() {
		if(id==null){
			employee = new Employee();
		}else{
			employee = employeeService.get(id);
		}
		employee.setDepartment(null);
	}
	@Override
	protected void prepareDeletee() {
	}
	
}
 