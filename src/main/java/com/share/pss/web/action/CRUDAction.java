package com.share.pss.web.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

/**
 * @author MrZhang 
 * @date 2017年12月20日 下午12:51:11
 * @version V1.0 
 */
public abstract class CRUDAction<T> extends BaseAction implements ModelDriven<T>,Preparable{
	private static final long serialVersionUID = 1L;
	//拦截器栈中拦截器执行到modelDriven之前时栈顶是EmployeeAction(此时使用其中的id接收前台id参数),压栈之后栈顶就是Employee了。
	protected Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	//====================================Action方法=========================================
	@Override //列表展示employee->EmployeeAction+execute
	public String execute() throws Exception {
		list();
		return SUCCESS;
	}
	protected abstract void list();
	@Override //添加或者修改页面employee_input->EmployeeAction+input
	public String input() throws Exception {
		inputt();
		return INPUT;
	}
	protected abstract void inputt();
	//保存 employee_save->EmployeeAction+save
	@InputConfig(methodName=Action.INPUT)//当验证方法没有通过时，改变默认返回的input视图 或者方法
	public  String save() throws Exception{
		savee();
		return RELOAD;
	}
	protected abstract void savee();
	//删除 employee_delete->EmployeeAction+delete
	public String delete() throws Exception{
		deletee();
		return RELOAD;
	}
	protected abstract void deletee();
	//==================================实现ModelDriven和Prepareable接口解决属性丢失问题========================
	//Prepareable
	@Override
	public void prepare() throws Exception {
		preparee();
	}
	protected abstract void preparee();
	//ModelDriven调用此方法时如果不为空则压到栈顶（压到栈顶后，页面中对对象属性的操作就不用'.'了，后台Action中的对象也不需要getter/setter了）
	@Override
	public T getModel() {
		return getModell();
	}
	protected abstract T getModell();
	//=========================prepare拦截器对应的类PrepareInterceptor检测方法前缀ferfix='prepare'通过反射调用=======
	//执行input方法之前自动执行
	public void prepareInput() throws Exception {
		prepareInputt();
	}
	protected abstract void prepareInputt();
	//执行save方法之前自动执行
	public void prepareSave() throws Exception {
		prepareSavee();
	}
	protected abstract void prepareSavee();
	//执行delete方法之前自动执行
	public void prepareDelete() throws Exception {
		prepareDeletee();
	}
	protected abstract void prepareDeletee ();
}
