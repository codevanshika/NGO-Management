<%@page import="in.co.nog.mgt.bean.UserBean"%>
<%@page import="in.co.nog.mgt.controller.NGOView"%>
<%@page import="in.co.nog.mgt.model.UserModel"%>
<%@page import="in.co.nog.mgt.bean.RoleBean"%>
<%@page import="in.co.nog.mgt.model.RoleModel"%>
<%@page import="in.co.nog.mgt.util.DataUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.nog.mgt.controller.UserListCtl"%>
<%@page import="in.co.nog.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User List</title>
<script language="javascript">
	$(function() {
		$("#selectall").click(function() {
			$('.case').attr('checked', this.checked);
		});
		$(".case").click(function() {

			if ($(".case").length == $(".case:checked").length) {
				$("#selectall").attr("checked", "checked");
			} else {
				$("#selectall").removeAttr("checked");
			}

		});
	});
</script>
</head>
<body>
	<%@ include file="header.jsp"%>
<br>

 	<nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		
		<li class="breadcrumb-item active" aria-current="page">User List</li>
	</ol>
	</nav>
<form action="<%=NGOView.USER_LIST_CTL%>" method="post">
		<div id="feedback">
			<div class="container">
				<div class="col-md-9">
					<div class="form-area">
							
							<h3 style="margin-bottom: 15px; text-align: left;">User List</h3>
							<div class="form-row">
    							<div class="form-group col-lg-4">
								<input type="text" class="form-control"  name="firstName"
									placeholder="First Name" value="<%=ServletUtility.getParameter("firstName", request)%>" > 
							</div>
        					<div class="form-group col-lg-4">
								<input type="text" class="form-control"  name="login"
									placeholder="Login Id" value="<%=ServletUtility.getParameter("login", request)%>" > 
							</div>
							<div class="form-group col-lg-4">
							<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=UserListCtl.OP_SEARCH%>">&nbsp;or&nbsp;
							<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=UserListCtl.OP_RESET%>">
							</div>
							</div>
					</div>
				</div>
			</div>
		</div>
	<center>
		<b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
			<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
	</center>
	
	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th scope="col">Select </th>
				<th scope="col">S.No</th>
				<th scope="col">Profile</th>
				<th scope="col">Name</th>
				<th scope="col">Login</th>
				<th scope="col">Contact</th>
				<th scope="col">DOB</th>
				<th scope="col">Gender</th>
				<th scope="col">Role</th>
				<th scope="col">Edit</th>
			</tr>
		</thead>
		<tbody>
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;
					UserBean bean = null;
					
					List list = ServletUtility.getList(request);
					
					Iterator<UserBean> it = list.iterator();
					
					while (it.hasNext()) {
						bean = it.next();
				%>
			<tr>
				<%
						UserBean bean2 = (UserBean) session.getAttribute("user");
					%>
				<td ><input type="checkbox" class="case"
						name="ids" value="<%=bean.getId()%>"
						<%=(bean2.getId() == bean.getId()) ? "disabled" : ""%>></td>
				<td><%=index++%></td>
				<td><img width="100px" height="100px" src="<%=NGOView.APP_CONTEXT%>/images/<%=bean.getImage()%>"></td>
				<td><%=bean.getFirstName()+" "+bean.getLastName()%></td>
				<td><%=bean.getLogin()%></td>
				<td><%=bean.getMobileNo()%></td>
				<td><%=DataUtility.getDateString(bean.getDob())%></td>
				<td><%=bean.getGender()%></td>
				
				<% RoleModel rModel=new RoleModel();
					RoleBean rBean=rModel.findByPK(bean.getRoleId());
				%>
				<td><%=rBean.getName()%></td>
				<td >
						<%
							if (bean2.getId() == bean.getId()) {
						%><b>---</b>
						<%
							} else {
						%><a class="btn btn-primary pull-right" href="user?id=<%=bean.getId()%>">Edit</a> <%
 	}
 %>
					</td>
			</tr>
			
			<%} %>
		</tbody>
	</table>
	<hr>
	<table width="99%" style=" bottom: 45px">
				<tr>

					<td><input type="submit" name="operation" class="btn btn-primary pull-right" 
						value="<%=UserListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>
					<td><input type="submit" name="operation"
						class="btn btn-primary pull-right"  value="<%=UserListCtl.OP_NEW%>"></td>
					<td><input type="submit" name="operation" class="btn btn-danger pull-right" 
						value="<%=UserListCtl.OP_DELETE%>"
						<%=(list.size() == 0) ? "disabled" : ""%>></td>
					<%
						UserModel model = new UserModel();
					%>
					<td align="right"><input type="submit" name="operation" class="btn btn-primary pull-right" 
						value="<%=UserListCtl.OP_NEXT%>"
						<%=((list.size() < pageSize) || model.nextPK() - 1 == bean.getId()) ? "disabled" : ""%>></td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
</form>
<br>
	<%@ include file="footer.jsp"%>
</body>
</html>