<%@page import="in.co.nog.mgt.controller.LoginCtl"%>
<%@page import="in.co.nog.mgt.bean.UserBean"%>
<%@page import="in.co.nog.mgt.controller.NGOView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
  $( function() {
    $( "#datepicker" ).datepicker({
    	dateFormat : 'mm/dd/yy',
		defaultDate : "01/01/1983",
      changeMonth: true,
      changeYear: true,
      yearRange : '-35:-18'
    });
  } );
  $( function() {
	    $( "#datepicker1" ).datepicker({
	    	dateFormat : 'mm/dd/yy',
	      changeMonth: true,
	      changeYear: true,
	    });
	  } );
  </script>
</head>
<body>
 <%
    UserBean userBean = (UserBean) session.getAttribute("user");

    boolean userLoggedIn = userBean != null;

    String welcomeMsg = "Hi, ";

    if (userLoggedIn) {
        String role = (String) session.getAttribute("role");
        welcomeMsg += userBean.getFirstName();
        if(userBean.getRoleId()==1){
        	welcomeMsg +="(Admin)";
        }else  if(userBean.getRoleId()==2){
        	welcomeMsg +="(Volunteer)";
        }
        else if(userBean.getRoleId()==3){
        	welcomeMsg +="(Donor)";
        }
    } else {
        welcomeMsg += "Guest";
    }

%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
   <a class="navbar-brand" href="index.jsp">
    <img src="/NGO-Management/images/3.jpg" width="50px" height="50px" class="d-inline-block align-top" alt="">
   <b>NGO -MANAGEMENT</b>
  </a>
</nav>
 <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
 
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
    <%if(!userLoggedIn){ %>
      <li class="nav-item active">
        <a class="nav-link" href="<%=NGOView.WELCOME_CTL%>">Home <span class="sr-only">(current)</span></a>
      </li>
      <%} %>
      
      <%if(userLoggedIn){ %>
      
       <li class="nav-item active">
        <a class="nav-link" href="<%=NGOView.WELCOME_CTL%>">Home <span class="sr-only">(current)</span></a>
      
      <%if(userBean.getRoleId()==1){ %>
      
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          User
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=NGOView.USER_CTL%>">Add User</a>
          <a class="dropdown-item" href="<%=NGOView.USER_LIST_CTL%>">User List</a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=NGOView.APPLICATION_LIST_CTL%>">Application List</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=NGOView.TRANSACTION_LIST_CTL%>">Transaction List</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Fundraisers
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=NGOView.FUNDRAISER_CTL%>">Add Fundraisers</a>
          <a class="dropdown-item" href="<%=NGOView.FUNDRAISER_LIST_CTL%>">Fundraisers List</a>
        </div>
      </li>
     
      
      <!--<li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Category
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="">Add Category</a>
          <a class="dropdown-item" href="">Category List</a>
        </div>
      </li>--->
      
      <%}else if(userBean.getRoleId()==2){  %>
      
       <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Application
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
           <a class="dropdown-item" href="<%=NGOView.APPLICATION_CTL%>">Add Application</a>
          <a class="dropdown-item" href="<%=NGOView.APPLICATION_LIST_CTL%>">View Application</a>
        </div>
      </li>
      
      
      <li class="nav-item">
        <a class="nav-link" href="<%=NGOView.FUNDRAISER_LIST_CTL%>">Fundraiser List</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=NGOView.RECEIPT_LIST_CTL%>">Receipt List</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=NGOView.FEEDBACK_LIST_CTL%>">Feedback List</a>
      </li>
      
     
     <%}else if(userBean.getRoleId()==3){  %>
      
       <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Donate
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
           <a class="dropdown-item" href="<%=NGOView.DONOR_MONEY_CTL%>"> Donate Money</a>
          <a class="dropdown-item" href="<%=NGOView.DONOR_THINGS_CTL%>">Donate Things</a>
        </div>
      </li>
      
      
     
      <li class="nav-item">
        <a class="nav-link" href="<%=NGOView.DONOR_FEEDBACK_CTL%>">Feedback</a>
      </li>
      
      
    <%}}else{ %>
    <li class="nav-item">
        <a class="nav-link" href="<%=NGOView.LOGIN_CTL%>">Our Team</a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link" href="<%=NGOView.LOGIN_CTL%>">Our Campaigns</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=NGOView.LOGIN_CTL%>">Workshops and Events</a>
      </li>
     
     	<li class="nav-item">
        <a class="nav-link" href="<%=NGOView.LOGIN_CTL%>">Login</a>
      </li>
      
    <%} %>
    </ul>
    
  
  </div>
  
  	
   	<ul class="nav justify-content-end">
  	
 	<%if(userLoggedIn) {%>
 	<li class="nav-item">
        <img alt="" height="45px" width="45px" src="/NGO-Management/images/<%=userBean.getImage()%>">
      </li>
 	 <li class="nav-item dropdown">
 	
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <%=welcomeMsg%>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=NGOView.MY_PROFILE_CTL%>">My Profile</a>
          <a class="dropdown-item" href="<%=NGOView.CHANGE_PASSWORD_CTL%>">Change Password</a>
          <a class="dropdown-item" href="<%=NGOView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</a>
        </div>
      </li>
  	<%} %>
  	
	</ul>
	
</nav>

</body>
</html>