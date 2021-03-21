
<%@page import="in.co.nog.mgt.controller.LoginCtl"%>
<%@page import="in.co.nog.mgt.util.ServletUtility"%>
<%@page import="in.co.nog.mgt.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<%@ include file="header.jsp" %>
<br>
 <nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		
		<li class="breadcrumb-item active" aria-current="page">Login</li>
	</ol>
	</nav>
	
	
<div col-md-5img-thumbnail">
		<div id="feedback">
			<div class="container">
				<div class="col-md-5">
					<div class="form-area">
						<form role="form" action="<%=NGOView.LOGIN_CTL%>" method="post" >
							<br style="clear: both">
		
							<jsp:useBean id="bean" class="in.co.nog.mgt.bean.UserBean"
         					   scope="request"></jsp:useBean>
         			   <% String uri=(String)request.getAttribute("uri");%>
		
             			 <input type="hidden" name="uri" value="<%=uri%>">
             			 
             	<input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
            
							<h3 style="margin-bottom: 15px; text-align: left: ;">Login</h3>
							
								<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                
              <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font></b>
							
							
							<div class="form-group">
								<input type="text" class="form-control"  name="login"
									placeholder="Login Id" value="<%=DataUtility.getStringData(bean.getLogin())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
							</div>
							<div class="form-group">
								<input type="password" class="form-control" 
									name="password" placeholder="Password" value="<%=DataUtility.getStringData(bean.getPassword()) %>" >
						<font   color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
							</div>
							<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=LoginCtl.OP_SIGN_IN %>">
								<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=LoginCtl.OP_SIGN_UP %>">
								<a href="<%=NGOView.FORGET_PASSWORD_CTL%>"><b>Forget my password?</b></a>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!--feedback-->
		<br>
		<div style="margin-top: 205px">
		<%@ include file="footer.jsp"%>
		</div>

</body>
</html>