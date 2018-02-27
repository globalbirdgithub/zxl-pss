package com.share.pss.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.share.pss.domain.Employee;
import com.share.pss.query.EmployeeQuery;
import com.share.pss.query.PageList;

/**
 * @author MrZhang 
 * @date 2017年10月31日 下午4:31:21
 * @version V1.0 测试类
 */
public class EmployeeServiceTest extends BaseServiceTest{
	@Autowired
	private IEmployeeService employeeService;
	@Test
	public void testGetAll() throws Exception {
		List<Employee> list = employeeService.getAll();
		System.out.println(list.size());
	}
	@Test
	public void testAdd() throws Exception {
		for (int i = 55; i < 81; i++) {
			Employee employee = new Employee();
			employee.setUsername("admin"+i);
			employee.setPassword("admin"+i);
			employee.setEmail("amdin"+i+"@itsource.cn");
			employeeService.saveOrUpdate(employee);
		}
	}
	@Test
	public void testQuery() throws Exception {
		//分页查询
		EmployeeQuery employeeQuery = new EmployeeQuery();
		//高级查询
//		employeeQuery.setUsername("xxx");
//		employeeQuery.setEmail("xxx");
		employeeQuery.setCurrentPage(11);
		employeeQuery.setPageSize(-3);
		PageList pageList = employeeService.findByQuery(employeeQuery);
		System.out.println(pageList);
	}
}
