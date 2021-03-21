
<%@page import="in.co.nog.mgt.controller.ChangePasswordCtl"%>
<%@page import="in.co.nog.mgt.util.ServletUtility"%>
<%@page import="in.co.nog.mgt.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
</head>
<body>
<%@ include file="header.jsp" %>
<br>
 <nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		
		<li class="breadcrumb-item active" aria-current="page">Change Password</li>
	</ol>
	</nav>

<div col-md-5 img-thumbnail">
           <div id="feedback"> <div class="container">
<div class="col-md-5">
    <div class="form-area">  
        <form role="form" action="<%=NGOView.CHANGE_PASSWORD_CTL%>" method="post" >
        
       <jsp:useBean id="bean" class="in.co.nog.mgt.bean.UserBean"
         					   scope="request"></jsp:useBean>
         					   
         					   <input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
			
        <br style="clear:both">
                    <h3 style="margin-bottom: 15px; text-align: left: ;">Change Password</h3>
                    <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                
              <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font></b>
                		
							<div class="form-group">
								<input type="text" class="form-control"  name="oldPassword"
									placeholder="Old Password" value=<%=DataUtility
                    .getString(request.getParameter("oldPassword") == null ? ""
                            : DataUtility.getString(request
                                    .getParameter("oldPassword")))%> > 
									<font  color="red"><%=ServletUtility.getErrorMessage("oldPassword", request)%></font>
							</div>
							<div class="form-row">
    							<div class="form-group col-md-6">
								<input type="password" class="form-control" 
									name="newPassword" placeholder="New Password" value=<%=DataUtility.getString(request.getParameter("newPassword") == null ? ""
                            : DataUtility.getString(request.getParameter("newPassword")))%> >
						<font   color="red"> <%=ServletUtility.getErrorMessage("newPassword", request)%></font>
							</div>
							
							<div class="form-group col-md-6">
								<input type="password" class="form-control" 
									name="confirmPassword" placeholder="Confirm Password" value=<%=DataUtility.getString(request
                    .getParameter("confirmPassword") == null ? "" : DataUtility
                    .getString(request.getParameter("confirmPassword")))%> >
						<font   color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
							</div>
							</div>
							
							
							
							
                    
            
        					<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=ChangePasswordCtl.OP_SAVE%>">
								
        </form>
    </div>
</div>
</div> </div> <!--feedback-->
<br>
		<div style="margin-top: 205px">
		<%@ include file="footer.jsp"%>
		</div>
</body>
</html>