<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>部门</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
		$('#pageNum').keyup(function(){//监听用户键盘输入事件
			this.value=this.value.replace(/\D/g,'');
		});
		$('#pageNum').pasteEvents = function(){//监听用户CTRL+V粘贴事件过滤字符
			this.value=this.value.replace(/\D/g,'');
		};
	});
</script>
</head>
<body>
	<div class="main-content">
		<!-- .面包屑导航 -->
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try {
					ace.settings.check('breadcrumbs', 'fixed')
				} catch (e) {
				}
			</script>
			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i><a href="#">首页</a></li>
				<li><a href="#">表格</a></li>
				<li class="active">动态</li>
			</ul>
		</div>

		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<div class="table-header">部门列表</div>
					<div class="table-responsive">
						<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
						
							<div class="row">
								<form action="department.action" id="pageForm" method="post">
									<div class="col-sm-6">
										<div>
											<label> 显示 <s:select list="{10,15,20}" name="departmentQuery.pageSize" onchange="javascript:$('#pageForm').submit();"></s:select></label>
											跳转<input type="text" id="pageNum" name="departmentQuery.currentPage" value="${pageList.currentPage}" size="2" oncontextmenu="return false"/>
											<button class="btn-pink" type="submit">GO</button>
										</div>
									</div>
									<div class="col-sm-6">
										<input type="text" placeholder="请输入部门名关键字..." name="departmentQuery.deptName" value="${departmentQuery.deptName}"/>
										<a class="btn btn-xs btn-pink" href="#" onclick="javascript:$('#pageForm').submit();">
											 <i class="icon-search">搜索</i>
										</a>
										<a class="btn btn-xs btn-pink" href="department_input.action">
											<i class="icon-credit-card">新建</i>	
										</a>
									</div>
								</form>
							</div>
							
							<table id="sample-table-2" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th class="center">
											<label> 
												<input type="checkbox" class="ace" /><span class="lbl"></span>
											</label>
										</th>
										<th>编号</th>
										<th>部门名称</th>
										<th><i class="icon-time"></i>操作</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="pageList.rows">
										<tr>
											<td class="center">
												<label> 
													<input type="checkbox" class="ace" /><span class="lbl"></span>
												</label>
											</td>
											<td>${id}</td>
											<td>${name}</td>
											<td>
												<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
													<a class="green" href="department_input.action?id=${id}">
														<i class="icon-pencil bigger-130"></i>
													</a> 
													<a class="red" href="department_delete.action?id=${id}">
														<i class="icon-trash bigger-130"></i>
													</a>
												</div>
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						
							<div class="row">
								<div class="col-sm-6">
									<div id="sample-talbe-2_info" class="dataTables_info">显示${pageList.beginIndex}到${pageList.endIndex}共${pageList.totalCount}条</div>
								</div>
								<div class="col-sm-6">
									<ul class="pagination pull-right no-margin">
										<s:property value="pageList.goPage" escapeHtml="false"/>
										<script type="text/javascript">
											function goPage(number){
												$('#pageNum').val(number);
												$('#pageForm').submit();
											}
										</script>
									</ul>
								</div>
							</div>
								
						</div><!-- /.modal-content -->
					</div><!-- /.modal-dialog -->
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div><!-- /.page-content -->
	</div><!-- /.main-content -->
</body>
</html>