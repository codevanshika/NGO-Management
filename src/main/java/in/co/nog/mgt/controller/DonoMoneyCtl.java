package in.co.nog.mgt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


import in.co.nog.mgt.bean.ApplicationBean;
import in.co.nog.mgt.bean.BaseBean;
import in.co.nog.mgt.bean.DonorMoneyBean;
import in.co.nog.mgt.bean.UserBean;
import in.co.nog.mgt.exception.ApplicationException;
import in.co.nog.mgt.exception.DuplicateRecordException;
import in.co.nog.mgt.model.ApplicationModel;
import in.co.nog.mgt.model.DonorMoneyModel;
import in.co.nog.mgt.util.DataUtility;
import in.co.nog.mgt.util.DataValidator;
import in.co.nog.mgt.util.PropertyReader;
import in.co.nog.mgt.util.ServletUtility;

/**
 * Servlet implementation class ApplicationCtl
 */
@ WebServlet(name="DonoMoneyCtl",urlPatterns={"/ctl/donorMoney"})
public class DonoMoneyCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(DonoMoneyCtl.class);
	/**
	 * Loads list and other data required to display at HTML form
	 * 
	 * @param request
	 */
	
	
	
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("ApplicationCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name",
					PropertyReader.getValue("error.require", " Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email",
					PropertyReader.getValue("error.require", " Email"));
			pass = false;
		}
		
		 

		if (DataValidator.isNull(request.getParameter("accNo"))) {
			request.setAttribute("accNo",
					PropertyReader.getValue("error.require", " Account Number"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("bName"))) {
			request.setAttribute("bName",
					PropertyReader.getValue("error.require", " Bank Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("ifscCode"))) {
			request.setAttribute("ifscCode",
					PropertyReader.getValue("error.require", "IFSC Code"));
			pass = false;
		}

		
		if (DataValidator.isNull(request.getParameter("amount"))) {
			request.setAttribute("amount",
					PropertyReader.getValue("error.require", "Amount"));
			pass = false;
		}
		


		log.debug("ApplicationCtl Method validate Ended");

		return pass;
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("ApplicationCtl Method populatebean Started");

		DonorMoneyBean bean = new DonorMoneyBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setBankname(DataUtility.getString(request.getParameter("bName")));
		bean.setAccountNo(DataUtility.getString(request.getParameter("accNo")));
		bean.setIFSCCode(DataUtility.getString(request.getParameter("ifscCode")));
		bean.setAmount(DataUtility.getString(request.getParameter("amount")));
		populateDTO(bean, request);

		log.debug("ApplicationCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("ApplicationCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        
	DonorMoneyModel model = new DonorMoneyModel();
        
		long id = DataUtility.getLong(request.getParameter("id"));
       
		if (id > 0 || op != null) {
          
			DonorMoneyBean bean;
            try {
                bean = model.findByPK(id);
             
                ServletUtility.setBean(bean, request);
            
            } catch (ApplicationException e) {
                log.error(e);
            
                ServletUtility.handleException(e, request, response);
                return;
            }
        }

        ServletUtility.forward(getView(), request, response);
        log.debug("ApplicationCtl Method doGet Ended");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("ApplicationCtl Method doPost Started");
        String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        DonorMoneyModel model = new DonorMoneyModel();
        long id = DataUtility.getLong(request.getParameter("id"));
        if (OP_SAVE.equalsIgnoreCase(op)) {
        	DonorMoneyBean bean = (DonorMoneyBean) populateBean(request);
            HttpSession session=request.getSession();
            UserBean uBean=(UserBean)session.getAttribute("user");
            bean.setUserId(uBean.getId());
            try {
                if (id > 0) {
                    model.update(bean);
                    ServletUtility.setSuccessMessage("Data is successfully Updated", request);
                } else {
                    long pk = model.add(bean);
                   // bean.setId(pk);
                    ServletUtility.setSuccessMessage("Data is successfully saved",request);
                }
              
               
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage(e.getMessage(), request);
            }
            ServletUtility.forward(getView(), request, response);
        } else if (OP_DELETE.equalsIgnoreCase(op)) {

        	DonorMoneyBean bean = (DonorMoneyBean) populateBean(request);
            try {
                model.delete(bean);
                ServletUtility.redirect(NGOView.APPLICATION_LIST_CTL, request,
                        response);
                return;
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
              
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
        	ServletUtility.redirect(NGOView.APPLICATION_LIST_CTL, request, response);
        	
        }else if (OP_RESET.equalsIgnoreCase(op)) {
    		ServletUtility.redirect(NGOView.DONOR_MONEY_CTL, request, response);
    		return;
    }
    					
        ServletUtility.forward(getView(), request, response);
        

        log.debug("ApplicationCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return NGOView.DONOR_MONEY_VIEW;
	}

}
