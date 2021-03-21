
<%@page import="in.co.nog.mgt.model.UserModel"%>
<%@page import="in.co.nog.mgt.controller.ApplicationCtl"%>
<%@page import="in.co.nog.mgt.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.nog.mgt.util.ServletUtility"%>
<%@page import="in.co.nog.mgt.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Receipt</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<br>
	<nav aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">

		<li class="breadcrumb-item active" aria-current="page">Donor
			Receipt</li>
	</ol>
	</nav>
 <jsp:useBean id="bean" class="in.co.nog.mgt.bean.DonorMoneyBean"
         					   scope="request"></jsp:useBean>
	<div class="container">
		<div class="card">
			<div class="card-header">
				Invoice <strong><%=DataUtility.getStringData(bean.getCreatedDatetime())%></strong> <span class="float-right">
					<strong>Status:</strong> Done
				</span>

			</div>
			<div class="card-body">
				<div class="row mb-4">
					<div class="col-sm-6">
						<h6 class="mb-3">From:</h6>
						<div>
							<strong><%=bean.getName()%></strong>
						</div>
						<div>Email:<%=bean.getEmail()%></div>
						<div>Phone:<%=new UserModel().findByPK(bean.getUserId()).getMobileNo()%></div>
					</div>

					<div class="col-sm-6">
					<img width="100px" height="100px" src="<%=NGOView.APP_CONTEXT%>/images/<%=new UserModel().findByPK(bean.getUserId()).getImage()%>">
					</div>



				</div>

				<div class="table-responsive-sm">
					<table class="table table-striped">
						<thead>
							<tr>
								<th class="center">#</th>
								<th>AccountNo</th>
								<th>Bank Name</th>
								<th>Amount</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="center">1</td>
								<td class="left strong"><%=bean.getAccountNo()%></td>
								<td class="left"><%=bean.getBankname()%></td>
								<td class="right"><%=bean.getAmount()%></td>
							</tr>
						</tbody>
					</table>
				</div>
				

			</div>
		</div>
	</div>
	<br>

	<%@ include file="footer.jsp"%>
</body>
</html>