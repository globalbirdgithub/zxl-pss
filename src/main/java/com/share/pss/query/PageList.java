package com.share.pss.query;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MrZhang
 * @date 2017年12月13日 上午2:36:49
 * @version V1.0 封装查询结果
 */
public class PageList {
	private int currentPage;
	private int pageSize;
	private int totalCount;
	private int totalPage;
	private List<Object> rows = new ArrayList<>();

	public PageList() {
	}
	
	public PageList(int currentPage, int pageSize, int totalCount) {
		this.currentPage = currentPage < 1 ? 1 : currentPage;
		this.pageSize = pageSize < 1 ? 10 : pageSize;
		this.totalCount = totalCount;
		this.totalPage = (this.totalCount % this.pageSize) == 0 ? this.totalCount / this.pageSize : this.totalCount / this.pageSize + 1;
		this.currentPage = this.currentPage > this.totalPage ? this.totalPage : this.currentPage;
	}

	// 分页条操作信息
	@SuppressWarnings("unused")
	private int beginIndex;
	@SuppressWarnings("unused")
	private int endIndex;
	@SuppressWarnings("unused")
	private String goPage;

	public int getBeginIndex() {
		return (this.currentPage - 1) * this.pageSize + 1;
	}

	public int getEndIndex() {
		return this.currentPage * this.pageSize > this.totalCount ? this.totalCount : this.currentPage * this.pageSize;
	}

	public String getGoPage() {
		StringBuilder sb = new StringBuilder(200);
		//首页
		if(this.currentPage==1){
			sb.append("<li class='prev disabled'><a href='#'>首页</a></li>");
			sb.append("<li class='prev disabled'><a href='#'>上一页</a></li>");
		}else{
			sb.append("<li class='prev'><a href='#' onclick='goPage(1)'>首页</a></li>");
			sb.append("<li class='prev'><a href='#' onclick='goPage("+(this.currentPage-1)+")'>上一页</a></li>");
		}
		//中间页
		for (int i = 1; i <= this.totalPage; i++) {
			if (this.currentPage == i) {
				sb.append("<li class='active'><a href='#'>" + i + "</a></li>");
			} else {
				sb.append("<li class='prev'><a href='#' onclick='goPage(" + i + ")'>" + i + "</a></li>");
			}
		}
		//尾页
		if(this.currentPage==this.totalPage){
			sb.append("<li class='next disabled'><a href='#'>下一页</a></li>");
			sb.append("<li class='next disabled'><a href='#'>尾页</a></li>");
		}else{
			sb.append("<li class='next'><a href='#' onclick='goPage("+(this.currentPage+1)+")'>下一页</a></li>");
			sb.append("<li class='next'><a href='#' onclick='goPage("+this.totalPage+")'>尾页</a></li>");
		}
		return sb.toString();
	}

	// 其他getter/setter
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

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "PageList [currentPage=" + currentPage + ", pageSize=" + pageSize + ", totalCount=" + totalCount
				+ ", totalPage=" + totalPage + ", rowsSize=" + rows.size() + "]";
	}

}
