<%@page import="in.co.nog.mgt.controller.MyProfileCtl"%>
<%@page import="in.co.nog.mgt.util.HTMLUtility"%>
<%@page import="in.co.nog.mgt.util.ServletUtility"%>
<%@page import="in.co.nog.mgt.util.DataUtility"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Profile</title>
</head>
<body>
<%@ include file="header.jsp" %>
<br>
 <nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		
		<li class="breadcrumb-item active" aria-current="page">My Profile</li>
	</ol>
	</nav>

<div col-md-5 img-thumbnail">
           <div id="feedback"> <div class="container">
<div class="col-md-5">
    <div class="form-area">  
        <form role="form" action="<%=NGOView.MY_PROFILE_CTL%>" method="post" >
        
       <jsp:useBean id="bean" class="in.co.nog.mgt.bean.UserBean"
         					   scope="request"></jsp:useBean>
         					   
         					   <input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
			
        <br style="clear:both">
                    <h3 style="margin-bottom: 15px; text-align: left: ;">My Profile</h3>
                    <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                
              <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font></b>
                		<div class="form-row">
    							<div class="form-group col-md-6">
								<input type="text" class="form-control"  name="firstName"
									placeholder="First Name" value="<%=DataUtility.getStringData(bean.getFirstName())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("firstName", request)%></font>
							</div>
        					<div class="form-group col-md-6">
								<input type="text" class="form-control"  name="lastName"
									placeholder="Last Name" value="<%=DataUtility.getStringData(bean.getLastName())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("lastName", request)%></font>
							</div>
							</div>
							<div class="form-group">
								<input type="text" class="form-control"  name="login" readonly="readonly"
									placeholder="Login Id" value="<%=DataUtility.getStringData(bean.getLogin())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
							</div>
							
							
							<div class="form-group">
								<input type="text" class="form-control"  name="mobileNo"
									placeholder="Mobile No." value="<%=DataUtility.getStringData(bean.getMobileNo())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("mobileNo", request)%></font>
							</div>
							
							<%
						
							HashMap map = new HashMap();
							map.put("Male", "Male");
							map.put("Female", "Female");
							%>
							
							<div class="form-group">
								<%=HTMLUtility.getList("gender",String.valueOf(bean.getGender()), map)%> 
									<font  color="red"><%=ServletUtility.getErrorMessage("gender", request)%></font>
							</div>
							
							<div class="form-group">
								<input type="text" class="form-control" id="datepicker" name="dob"
									placeholder="Date Of Birth" value="<%=DataUtility.getDateString(bean.getDob())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("dob", request)%></font>
							</div>
                    
            
        					<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=MyProfileCtl.OP_SAVE%>">&nbsp;or&nbsp;
								<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD%>">
        </form>
    </div>
</div>
</div> </div> <!--feedback-->
<br>
		<div style="margin-top: 43px">
		<%@ include file="footer.jsp"%>
		</div>
</body>
</html>