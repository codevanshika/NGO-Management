<%@page import="in.co.nog.mgt.util.DataUtility"%>
<%@page import="in.co.nog.mgt.model.ApplicationModel"%>
<%@page import="in.co.nog.mgt.bean.ApplicationBean"%>
<%@page import="in.co.nog.mgt.controller.ApplicationListCtl"%>
<%@page import="in.co.nog.mgt.util.ServletUtility"%>
<%@page import="in.co.nog.mgt.model.UserModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Application List</title>
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
		
		<li class="breadcrumb-item active" aria-current="page">Application List</li>
	</ol>
	</nav>
<form action="<%=NGOView.APPLICATION_LIST_CTL%>" method="post">
		<div id="feedback">
			<div class="container">
				<div class="col-md-9">
					<div class="form-area">
							
							<h3 style="margin-bottom: 15px; text-align: left;">Application List</h3>
							<div class="form-row">
    							<div class="form-group col-lg-4">
								<input type="text" class="form-control"  name="name"
									placeholder="Name" value="<%=ServletUtility.getParameter("name", request)%>" > 
							</div>
        					
							<div class="form-group col-lg-4">
							<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=ApplicationListCtl.OP_SEARCH%>">&nbsp;or&nbsp;
							<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=ApplicationListCtl.OP_RESET%>">
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
				<th scope="col">Select All</th>
				<th scope="col">S.No</th>
				<th scope="col">Profile</th>
				<th scope="col">Name</th>
				<th scope="col">Email</th>		
				<th scope="col">Dob</th>
				<th scope="col">Mobile</th>
				<th scope="col">Address</th>
				<th scope="col">Description</th>
				<th scope="col">Action</th>
			</tr>
		</thead>
		<tbody>
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;
					ApplicationBean bean = null;
					
					List list = ServletUtility.getList(request);
					
					Iterator<ApplicationBean> it = list.iterator();
					
					while (it.hasNext()) {
						bean = it.next();
				%>
			<tr>
				
				<td ><input type="checkbox" class="case"
						name="ids" value="<%=bean.getId()%>"
						></td>
				<td><%=index++%></td>
				<%
					UserModel uModel=new UserModel();
					 UserBean uBean =uModel.findByPK(bean.getUserId());
				%>
				<td><img width="100px" height="100px" src="<%=NGOView.APP_CONTEXT%>/images/<%=uBean.getImage()%>"></td>
				<td><%=bean.getName()%></td>
				<td><%=bean.getEmail()%></td>
				<td><%=DataUtility.getDateString(bean.getDob())%></td>
				<td><%=uBean.getMobileNo()%></td>
				<td><%=bean.getAddress()%></td>
				<td><%=bean.getDescription()%></td>
				<% if(userBean.getRoleId()==1){ %>
				<%if(bean.getStatus().equalsIgnoreCase("Accepted")||bean.getStatus().equalsIgnoreCase("Rejected")){ %>
				<td><%=bean.getStatus()%></td>
				<%}else{ %>
				<td >
						<a class="btn btn-primary pull-right" href="applicationList?aid=<%=bean.getId()%>&st=Accepted&op=Update">Accept</a>or
						<a class="btn btn-primary pull-right" href="applicationList?aid=<%=bean.getId()%>&st=Rejected&op=Update">Reject</a>
					</td>
					<%} %>
				<%}else if(userBean.getRoleId()==2){ %>	
				<td><%=bean.getStatus()%></td>
				<%} %>
			</tr>
			
			<%} %>
		</tbody>
	</table>
	<hr>
	<table width="99%" style=" bottom: 45px">
				<tr>

					<td><input type="submit" name="operation" class="btn btn-primary pull-right" 
						value="<%=ApplicationListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>
					
					
					<%
						ApplicationModel model = new ApplicationModel();
					%>
					<td align="right"><input type="submit" name="operation" class="btn btn-primary pull-right" 
						value="<%=ApplicationListCtl.OP_NEXT%>"
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