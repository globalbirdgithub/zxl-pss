package com.share.pss.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.share.pss.domain.Department;
import com.share.pss.query.DepartmentQuery;
import com.share.pss.query.PageList;

/**
 * @author MrZhang 
 * @date 2017年10月31日 下午4:31:21
 * @version V1.0 测试类
 */
public class DepartmentServiceTest extends BaseServiceTest{
	@Autowired
	IDepartmentService departmentService;
	@Test
	public void testCRUD() throws Exception {
		List<Department> all = departmentService.getAll();
		for (Department department : all) {
			System.out.println(department);
		}
	}
	@Test
	public void testQuery() throws Exception {
		DepartmentQuery departmentQuery = new DepartmentQuery();
		departmentQuery.setPageSize(15);
		departmentQuery.setCurrentPage(2);
		PageList pageList = departmentService.findByQuery(departmentQuery);
		System.out.println(pageList);
	}
}
