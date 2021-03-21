
<%@page import="in.co.nog.mgt.util.ServletUtility"%>
<%@page import="in.co.nog.mgt.controller.ForgetPasswordCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forget Password</title>
</head>
<body>
<%@ include file="header.jsp" %>
<br>
 <nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		
		<li class="breadcrumb-item active" aria-current="page">Forget Password</li>
	</ol>
	</nav>
	
<div col-md-5img-thumbnail">
		<div id="feedback">
			<div class="container">
				<div class="col-md-5">
					<div class="form-area">
						<form role="form" action="<%=NGOView.FORGET_PASSWORD_CTL%>" method="post" >
							<br style="clear: both">
		
							<jsp:useBean id="bean" class="in.co.nog.mgt.bean.UserBean"
         					   scope="request"></jsp:useBean>
         						 <input type="hidden" name="id" value="<%=bean.getId()%>">
            
							<h3 style="margin-bottom: 15px; text-align: left: ;">Forget Password</h3>
							
								<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
              				  </font></b>
                
            				  <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
              				  </font></b>
							
							
							<div class="form-group">
								<input type="text" class="form-control"  name="login"
									placeholder="Login Id" value="<%=ServletUtility.getParameter("login", request)%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
							</div>
							
							<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=ForgetPasswordCtl.OP_GO%>">
						</form>
					</div>
				</div>
			</div>
		</div>
		<!--feedback-->
		<br>
		<div style="margin-top: 259px">
		<%@ include file="footer.jsp"%>
		</div>
</body>
</html>