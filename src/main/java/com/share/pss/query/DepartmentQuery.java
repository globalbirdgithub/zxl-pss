package com.share.pss.query;

import org.apache.commons.lang3.StringUtils;

import com.share.pss.domain.Department;

public class DepartmentQuery extends BaseQuery {
	// 高级查询条件
	private String deptName;

	// 传递查询Object
	public DepartmentQuery(){
		super(Department.class.getSimpleName());
	}

	@Override
	protected void addCondition() {
		if (StringUtils.isNoneBlank(deptName)) {
			addCondition("o.name like ?", "%" + deptName + "%");
		}
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
