package in.co.nog.mgt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.nog.mgt.bean.FundraiserBean;
import in.co.nog.mgt.bean.BaseBean;
import in.co.nog.mgt.bean.UserBean;
import in.co.nog.mgt.exception.ApplicationException;
import in.co.nog.mgt.exception.DuplicateRecordException;
import in.co.nog.mgt.model.FundraiserModel;
import in.co.nog.mgt.util.DataUtility;
import in.co.nog.mgt.util.DataValidator;
import in.co.nog.mgt.util.PropertyReader;
import in.co.nog.mgt.util.ServletUtility;

/**
 * Servlet implementation class ApplicationCtl
 */
@ WebServlet(name="FundraiserCtl",urlPatterns={"/ctl/fundraiser"})
public class FundraiserCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(FundraiserCtl.class);
	/**
	 * Loads list and other data required to display at HTML form
	 * 
	 * @param request
	 */
	
	
	
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("FundraiserCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name",
					PropertyReader.getValue("error.require", " Name"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("date"))) {
			request.setAttribute("date",
					PropertyReader.getValue("error.require", "Date"));
			pass = false;
		}
		 

		
		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description",
					PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}
		


		log.debug("FundraiserCtl Method validate Ended");

		return pass;
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("FundraiserCtl Method populatebean Started");

		FundraiserBean bean = new FundraiserBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDate(DataUtility.getDate(request.getParameter("date")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));

		populateDTO(bean, request);

		log.debug("FundraiserCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("FundraiserCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        
		FundraiserModel model = new FundraiserModel();
        
		long id = DataUtility.getLong(request.getParameter("id"));
       
		if (id > 0 || op != null) {
          
            
			FundraiserBean bean;
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
        log.debug("FundraiserCtl Method doGet Ended");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("FundraiserCtl Method doPost Started");
        String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        FundraiserModel model = new FundraiserModel();
        long id = DataUtility.getLong(request.getParameter("id"));
        if (OP_SAVE.equalsIgnoreCase(op)) {
            FundraiserBean bean = (FundraiserBean) populateBean(request);
            try {
                if (id > 0) {
                    /*model.update(bean);*/
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

        FundraiserBean bean = (FundraiserBean) populateBean(request);
            try {
                model.delete(bean);
                ServletUtility.redirect(NGOView.FUNDRAISER_LIST_CTL, request,
                        response);
                return;
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
              
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
        	ServletUtility.redirect(NGOView.FUNDRAISER_LIST_CTL, request, response);
        	
        }else if (OP_RESET.equalsIgnoreCase(op)) {
    		ServletUtility.redirect(NGOView.FUNDRAISER_CTL, request, response);
    		return;
    }
    					
        ServletUtility.forward(getView(), request, response);
        

        log.debug("FundraiserCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return NGOView.FUNDRAISER_VIEW;
	}

}
