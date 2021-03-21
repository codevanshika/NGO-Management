package in.co.nog.mgt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.nog.mgt.bean.BaseBean;
import in.co.nog.mgt.bean.DonorFeedbackBean;
import in.co.nog.mgt.bean.UserBean;
import in.co.nog.mgt.exception.ApplicationException;
import in.co.nog.mgt.exception.DuplicateRecordException;
import in.co.nog.mgt.model.DonorFeedbackModel;
import in.co.nog.mgt.util.DataUtility;
import in.co.nog.mgt.util.DataValidator;
import in.co.nog.mgt.util.PropertyReader;
import in.co.nog.mgt.util.ServletUtility;

/**
 * Servlet implementation class ApplicationCtl
 */
@ WebServlet(name="DonoFeedbackCtl",urlPatterns={"/ctl/donorFeedback"})
public class DonoFeedbackCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(DonoFeedbackCtl.class);
	/**
	 * Loads list and other data required to display at HTML form
	 * 
	 * @param request
	 */
	
	
	
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("DonorFeedbackCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name",
					PropertyReader.getValue("error.require", " Name"));
			pass = false;
		}
		
		
		 

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description",
					PropertyReader.getValue("error.require", " Description"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("descriptionOne"))) {
			request.setAttribute("descriptionOne",
					PropertyReader.getValue("error.require", " DescriptionOne"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("descriptionTwo"))) {
			request.setAttribute("descriptionTwo",
					PropertyReader.getValue("error.require", " DescriptionTwo"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("descriptionThree"))) {
			request.setAttribute("descriptionThree",
					PropertyReader.getValue("error.require", " DescriptionThree"));
			pass = false;
		}
		
	


		log.debug("DonorFeedbackCtl Method validate Ended");

		return pass;
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("ApplicationCtl Method populatebean Started");

		DonorFeedbackBean bean = new DonorFeedbackBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setDescriptionOne(DataUtility.getString(request.getParameter("descriptionOne")));
		bean.setDescriptionTwo(DataUtility.getString(request.getParameter("descriptionTwo")));
		bean.setDescriptionThree(DataUtility.getString(request.getParameter("descriptionThree")));
		populateDTO(bean, request);

		log.debug("DonorFeedbackCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("ApplicationCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        
	DonorFeedbackModel model = new DonorFeedbackModel();
        
		long id = DataUtility.getLong(request.getParameter("id"));
       
		if (id > 0 || op != null) {
          
			DonorFeedbackBean bean;
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
        log.debug("DonorFeedbackCtl Method doGet Ended");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("ApplicationCtl Method doPost Started");
        String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        DonorFeedbackModel model = new DonorFeedbackModel();
        long id = DataUtility.getLong(request.getParameter("id"));
        if (OP_SAVE.equalsIgnoreCase(op)) {
        	DonorFeedbackBean bean = (DonorFeedbackBean) populateBean(request);
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

        	DonorFeedbackBean bean = (DonorFeedbackBean) populateBean(request);
            try {
                model.delete(bean);
                ServletUtility.redirect(NGOView.FEEDBACK_LIST_CTL, request,
                        response);
                return;
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
              
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
        	ServletUtility.redirect(NGOView.FEEDBACK_LIST_CTL, request, response);
        	
        }else if (OP_RESET.equalsIgnoreCase(op)) {
    		ServletUtility.redirect(NGOView.DONOR_FEEDBACK_CTL, request, response);
    		return;
    }
    					
        ServletUtility.forward(getView(), request, response);
        

        log.debug("ApplicationCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		
		return NGOView.DONOR_FEEDBACK_VIEW;
	}

}
