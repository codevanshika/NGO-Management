
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
<title>Application</title>
</head>
<body>
<%@ include file="header.jsp" %>
<br>
 <nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		
		<li class="breadcrumb-item active" aria-current="page">Apply To Be A Volunteer</li>
	</ol>
	</nav>

<div col-md-5 img-thumbnail">
           <div id="feedback"> <div class="container">
<div class="col-md-5">
    <div class="form-area">  
        <form role="form" action="<%=NGOView.APPLICATION_CTL%>" method="post" >
        
       <jsp:useBean id="bean" class="in.co.nog.mgt.bean.ApplicationBean"
         					   scope="request"></jsp:useBean>
         					   
         					   <input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
			
        <br style="clear:both">
                    <h3 style="margin-bottom: 15px; text-align: left: ;">Apply To Be A Volunteer</h3>
                    <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                
              <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font></b>
                
                
                
                
                
                		<div class="form-group">
								<input type="text" class="form-control" readonly="readonly" name="name"
									placeholder="Name" value="<%=DataUtility.getStringData(userBean.getFirstName()+" "+userBean.getLastName())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("name", request)%></font>
							</div>
							
							
							<div class="form-group">
								<input type="text" class="form-control"  name="email"
									placeholder="Email" value="<%=DataUtility.getStringData(bean.getEmail())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("email", request)%></font>
							</div>
							
							<div class="form-group">
								<input type="text" class="form-control" readonly="readonly"  name="dob"
									placeholder="Date of Birth" value="<%=DataUtility.getDateString(userBean.getDob())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("dob", request)%></font>
							</div>
							
							
							<div class="form-group">
								<input type="text" class="form-control"  name="qual"
									placeholder="Qualification" value="<%=DataUtility.getStringData(bean.getQualification())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("qual", request)%></font>
							</div>
							
							<div class="form-group">
                   				 <textarea class="form-control" type="textarea" name="address" placeholder="Address"  rows="3"><%=DataUtility.getStringData(bean.getAddress())%></textarea>
                                           <font  color="red"><%=ServletUtility.getErrorMessage("address", request)%></font>
                  			  </div>
                  			  	<div class="form-group">
                   				 <textarea class="form-control" type="textarea" name="description" placeholder="Please indicate areas to volunteer according to your skills"  rows="3"><%=DataUtility.getStringData(bean.getDescription())%></textarea>
                                           <font  color="red"><%=ServletUtility.getErrorMessage("description", request)%></font>
                  			  </div>
							
        					<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=ApplicationCtl.OP_SAVE%>">&nbsp;or&nbsp;
								<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=ApplicationCtl.OP_RESET%>">
        </form>
    </div>
</div>
</div> </div> <!--feedback-->
<br>

		<%@ include file="footer.jsp"%>
</body>
</html>