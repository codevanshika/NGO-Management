
<%@page import="in.co.nog.mgt.controller.DonoMoneyCtl"%>
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
<title>Donate Money</title>
</head>
<body>
<%@ include file="header.jsp" %>
<br>
 <nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		
		<li class="breadcrumb-item active" aria-current="page">Donate Money</li>
	</ol>
	</nav>

<div col-md-5 img-thumbnail">
           <div id="feedback"> <div class="container">
<div class="col-md-5">
    <div class="form-area">  
        <form role="form" action="<%=NGOView.DONOR_MONEY_CTL%>" method="post" >
        
       <jsp:useBean id="bean" class="in.co.nog.mgt.bean.DonorMoneyBean"
         					   scope="request"></jsp:useBean>
         					   
         					   <input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
			
        <br style="clear:both">
                    <h3 style="margin-bottom: 15px; text-align: left: ;">Donate Money</h3>
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
									placeholder="EmailAddress" value="<%=DataUtility.getStringData(bean.getEmail())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("email", request)%></font>
							</div>
							
							<div class="form-group">
								<input type="text" class="form-control"  name="accNo"
									placeholder="Enter  your Account Number.." value="<%=DataUtility.getStringData(bean.getAccountNo())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("accNo", request)%></font>
							</div>
							
							
							<div class="form-group">
								<input type="text" class="form-control"  name="bName"
									placeholder="Enter Bank you want to donate from" value="<%=DataUtility.getStringData(bean.getBankname())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("bName", request)%></font>
							</div>
							
							<div class="form-group">
								<input type="text" class="form-control"  name="ifscCode"
									placeholder="IFSCCode" value="<%=DataUtility.getStringData(bean.getIFSCCode())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("ifscCode", request)%></font>
							</div>
							
							<div class="form-group">
								<input type="text" class="form-control"  name="amount"
									placeholder="Enter your amount you want to donate" value="<%=DataUtility.getStringData(bean.getAmount())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("amount", request)%></font>
							</div>
						
							
        					<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=DonoMoneyCtl.OP_SAVE%>">&nbsp;or&nbsp;
								<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=DonoMoneyCtl.OP_RESET%>">
        </form>
    </div>
</div>
</div> </div> <!--feedback-->
<br>

		<%@ include file="footer.jsp"%>
</body>
</html>