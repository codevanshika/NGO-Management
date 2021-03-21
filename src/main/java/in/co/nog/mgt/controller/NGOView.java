package in.co.nog.mgt.controller;

public interface NGOView {
	
	public String APP_CONTEXT = "/NGO-Management";

	public String PAGE_FOLDER = "/jsp";

	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";

	
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";	
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	
	
	public String APPLICATION_VIEW = PAGE_FOLDER + "/ApplicationView.jsp";	
	public String APPLICATION_LIST_VIEW = PAGE_FOLDER + "/ApplicationListView.jsp";
	
	public String FUNDRAISER_VIEW = PAGE_FOLDER + "/FundraiserView.jsp";	
	public String FUNDRAISER_LIST_VIEW = PAGE_FOLDER + "/FundraiserListView.jsp";
	
	public String DONOR_MONEY_VIEW = PAGE_FOLDER + "/DonorMoneyView.jsp";
	public String TRANSACTION_LIST_VIEW = PAGE_FOLDER + "/TransactionListView.jsp";
	public String RECEIPT_LIST_VIEW = PAGE_FOLDER + "/ReceiptListView.jsp";
	public String DONOR_THINGS_VIEW = PAGE_FOLDER + "/DonorThingsView.jsp";	
	
	public String DONOR_FEEDBACK_VIEW = PAGE_FOLDER + "/DonorFeedbackView.jsp";	
	public String FEEDBACK_LIST_VIEW = PAGE_FOLDER + "/FeedbackListView.jsp";
	
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	
	public String RECEIPT_VIEW = PAGE_FOLDER + "/ReceiptView.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";

	
	

	public String ERROR_CTL = "/ctl/ErrorCtl";

	public String DONOR_MONEY_CTL = APP_CONTEXT + "/ctl/donorMoney";
	public String DONOR_THINGS_CTL = APP_CONTEXT + "/ctl/donorThings";
	public String TRANSACTION_LIST_CTL = APP_CONTEXT + "/ctl/transactionList";
	
	
	public String DONOR_FEEDBACK_CTL = APP_CONTEXT + "/ctl/donorFeedback";
	public String FEEDBACK_LIST_CTL = APP_CONTEXT + "/ctl/feedbackList";
	
	public String USER_CTL = APP_CONTEXT + "/ctl/user";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/userList";
	
	public String APPLICATION_CTL = APP_CONTEXT + "/ctl/application";
	public String APPLICATION_LIST_CTL = APP_CONTEXT + "/ctl/applicationList";
	
	public String FUNDRAISER_CTL = APP_CONTEXT + "/ctl/fundraiser";
	public String FUNDRAISER_LIST_CTL = APP_CONTEXT + "/ctl/fundraiserList";
	
	public String RECEIPT_CTL = APP_CONTEXT + "/ctl/receipt";
	public String RECEIPT_LIST_CTL = APP_CONTEXT + "/ctl/receiptList";
	
	
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/register";
	public String LOGIN_CTL = APP_CONTEXT + "/login";
	public String WELCOME_CTL = APP_CONTEXT + "/welcome";
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/changePassword";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/myProfile";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/forgetPassword";



}
