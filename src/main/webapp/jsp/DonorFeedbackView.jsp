
<%@page import="in.co.nog.mgt.controller.DonoFeedbackCtl"%>
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
<title>Give Feedback</title>
</head>
<body>
<%@ include file="header.jsp" %>
<br>
 <nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		
		<li class="breadcrumb-item active" aria-current="page">Give Feedback</li>
	</ol>
	</nav>

<div col-md-5 img-thumbnail">
           <div id="feedback"> <div class="container">
<div class="col-md-5">
    <div class="form-area">  
        <form role="form" action="<%=NGOView.DONOR_FEEDBACK_CTL%>" method="post" >
        
       <jsp:useBean id="bean" class="in.co.nog.mgt.bean.DonorFeedbackBean"
         					   scope="request"></jsp:useBean>
         					   
         					   <input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
			
        <br style="clear:both">
                    <h3 style="margin-bottom: 15px; text-align: left: ;">Suggestions Are Always Welcomed!</h3>
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
								<input type="text" class="form-control"  name="description"
									placeholder="What do you like most about our organisation.." value="<%=DataUtility.getStringData(bean.getDescription())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("description", request)%></font>
									
							</div>
							<br>
							
							<div class="form-group">
								<input type="text"  class="form-control"  name="descriptionOne"
									placeholder="What improvements could be made to our fundraiser.." value="<%=DataUtility.getStringData(bean.getDescriptionOne())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("descriptionOne", request)%></font>
									
							</div>
							<br>
							
							<div class="form-group">
								<input type="text" style="height:100px;width:530px;"  class="form-control"  name="descriptionTwo"
									placeholder="Overall, were you satisfied with your experience at our organisation?.." value="<%=DataUtility.getStringData(bean.getDescriptionTwo())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("descriptionTwo", request)%></font>
									
							</div>
							<br>
							
							<div class="form-group">
								<input type="text" style="height:100px;width:530px;" class="form-control"  name="descriptionThree"
									placeholder="What recommendations do you have for future fundraising events?.." value="<%=DataUtility.getStringData(bean.getDescriptionThree())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("descriptionThree", request)%></font>
									
							</div>
							
							
								
							
        					<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=DonoFeedbackCtl.OP_SAVE%>">&nbsp;or&nbsp;
								<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=DonoFeedbackCtl.OP_RESET%>">
        </form>
    </div>
</div>
</div> </div> <!--feedback-->
<br>

		<%@ include file="footer.jsp"%>
</body>
</html>