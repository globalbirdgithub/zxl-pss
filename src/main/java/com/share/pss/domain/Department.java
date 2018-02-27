package com.share.pss.domain;

/**
 * @author MrZhang
 * @date 2017年12月21日 上午11:37:44
 * @version V1.0 部门模型
 */
public class Department {
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}

}
