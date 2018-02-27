package com.share.pss.query;

import org.apache.commons.lang3.StringUtils;
import com.share.pss.domain.Employee;

/**
 * @author MrZhang
 * @date 2017年12月13日 上午2:36:19
 * @version V1.0 封装高级查询所需条件 用户名、部门、邮箱
 */
public class EmployeeQuery extends BaseQuery {
	// 高级查询参数
	private String username;
	private String email;
	private Long deptId;

	// 将类名Employee传递给父类的hql查询语句
	public EmployeeQuery() {
		super(Employee.class.getSimpleName());
	}

	// 覆写父类抽象方法；传递具体高级查询参数给父类；
	@Override
	protected void addCondition() {
		if (StringUtils.isNoneBlank(username)) {
			addCondition("o.username like ?", "%" + username + "%");
		}
		if (StringUtils.isNoneBlank(email)) {
			addCondition("o.email like ?", "%" + email + "%");
		}
		if (deptId != null && deptId != -1L) {
			addCondition("o.department.id=?", deptId);
		}
	}

	// 以下setter/getter提供给Struts2使用
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long depeId) {
		this.deptId = depeId;
	}
}
