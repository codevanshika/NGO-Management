package in.co.nog.mgt.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import in.co.nog.mgt.bean.BaseBean;
import in.co.nog.mgt.bean.UserBean;
import in.co.nog.mgt.exception.ApplicationException;
import in.co.nog.mgt.exception.DuplicateRecordException;
import in.co.nog.mgt.model.RoleModel;
import in.co.nog.mgt.model.UserModel;
import in.co.nog.mgt.util.DataUtility;
import in.co.nog.mgt.util.DataValidator;
import in.co.nog.mgt.util.PropertyReader;
import in.co.nog.mgt.util.ServletUtility;



/**
 * Servlet implementation class UserCtl
 */

/**
 * 
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 * 
 */

@ WebServlet(name="UserCtl",urlPatterns={"/ctl/user"})
@MultipartConfig(maxFileSize = 16177215)
public class UserCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(UserCtl.class);
	/**
	 * Loads list and other data required to display at HTML form
	 * 
	 * @param request
	 */
	@Override
	protected void preload(HttpServletRequest request) {
		log.debug("UserCtl preload method start");
		RoleModel model = new RoleModel();
		try {
			List l = model.list();
			request.setAttribute("roleList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}
		log.debug("UserCtl preload method end");
	}
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("UserCtl Method validate Started");

		boolean pass = true;

		String login = request.getParameter("login");
		String dob = request.getParameter("dob");
		if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require","Mobile No"));
			pass = false;
		}else if(!DataValidator.isPhoneNo(request.getParameter("mobile"))){
			request.setAttribute("mobile", PropertyReader.getValue("error.invalid","Mobile No"));
			pass=false;
		} 

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		}else if (!DataValidator.isName(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.name", "First Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		}else if (!DataValidator.isName(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.name", "LastName"));
			pass = false;
		}

		if (DataValidator.isNull(login)) {
			request.setAttribute("login",
					PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(login)) {
			request.setAttribute("login",
					PropertyReader.getValue("error.email", "Login "));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader.getValue("error.require", "Password"));
			pass = false;
		}else if (!DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader.getValue("error.password", "Password"));
			return false;
		}

		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader.getValue(
					"error.require", "Confirm Password"));
			pass = false;
		}

		if ("-----Select-----".equalsIgnoreCase(request.getParameter("gender"))) {
			request.setAttribute("gender",
					PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		/*if (DataValidator.isNull(request.getParameter("idProof"))) {
			request.setAttribute("idProof",
					PropertyReader.getValue("error.require", "id proof"));
			pass = false;
		}else if (!DataValidator.isName(request.getParameter("idProof"))) {
			request.setAttribute("idProof",
					PropertyReader.getValue("error.name", "idProof"));
			pass = false;/*

		/*if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender",
					PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}*/
		if ("-----Select-----".equalsIgnoreCase(request
				.getParameter("roleId"))) {
			request.setAttribute("roleId",
					PropertyReader.getValue("error.require", "Role Name"));
			pass = false;
		}
			if (DataValidator.isNull(dob)) {
				request.setAttribute("dob",
						PropertyReader.getValue("error.require", "Date Of Birth"));
				pass = false;
			} /*else if (!DataValidator.isDate(dob)) {
			request.setAttribute("dob",
					"Min Age Must be 17 years");
			pass = false;
		}*/
		if(DataValidator.isNull(request.getParameter("gender"))){
			System.out.println("gender"+request.getParameter("gender"));
			request.setAttribute("error.require", PropertyReader.getValue("Gender"));
			pass=false;
		} else if (DataValidator.isNotNull(request.getParameter("gender"))) {
			if ("Select".equals(request.getParameter("gender"))) {
				request.setAttribute("gender",
						PropertyReader.getValue("error.require", "Gender"));
				pass = false;
			}
		}
		
		if (DataValidator.isNull(request.getParameter("roleId"))) {
            request.setAttribute("roleId",
                    PropertyReader.getValue("error.require", "Role"));
            pass = false;
        }
        else if (DataValidator.isNotNull(request.getParameter("roleId"))) {
        	if ("Select".equals(request.getParameter("roleId"))) {
				request.setAttribute("roleId",
						PropertyReader.getValue("error.require", "Role"));
				pass = false;
			}
		}
		if (!request.getParameter("password").equals(
				request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			
			request.setAttribute("confirmPassword", PropertyReader.getValue("error.confirmPassword","Confirm Password"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("idProof"))) {
			request.setAttribute("idProof",
					PropertyReader.getValue("error.require", "Id Proof"));
			pass = false;
		}
		
		
		Part part = null;
		try {
			part = request.getPart("photo");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String imgName = Paths.get(part.getSubmittedFileName()).getFileName().toString();

		if (DataValidator.isNull(imgName)) {
			request.setAttribute("photo", PropertyReader.getValue("error.require", "Profile Picture"));
			pass = false;
		}

		log.debug("UserCtl Method validate Ended");

		return pass;
	}
	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("UserCtl Method populatebean Started");

		UserBean bean = new UserBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setRoleId(DataUtility.getLong(request.getParameter("roleId")));

		bean.setFirstName(DataUtility.getString(request	.getParameter("firstName")));

		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

		bean.setLogin(DataUtility.getString(request.getParameter("login")));

		bean.setPassword(DataUtility.getString(request.getParameter("password")));

		bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));

		bean.setGender(DataUtility.getString(request.getParameter("gender")));

		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		
		bean.setIdProof(DataUtility.getString(request.getParameter("idProof")));

		populateDTO(bean, request);

		log.debug("UserCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * Contains DIsplay logics
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("UserCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        
		UserModel model = new UserModel();
        
		long id = DataUtility.getLong(request.getParameter("id"));
       
		if (id > 0 || op != null) {
          
            
			UserBean bean;
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
        log.debug("UserCtl Method doGet Ended");
	}

	

/**
 * Contains Submit logics
 */
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("UserCtl Method doPost Started");
        String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        UserModel model = new UserModel();
        long id = DataUtility.getLong(request.getParameter("id"));
        if (OP_SAVE.equalsIgnoreCase(op)) {
            UserBean bean = (UserBean) populateBean(request);
            bean.setImage(SubImage(request, response));
            
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
                ServletUtility.setErrorMessage("Login id already exists", request);
            }
            ServletUtility.forward(getView(), request, response);
        } else if (OP_DELETE.equalsIgnoreCase(op)) {

            UserBean bean = (UserBean) populateBean(request);
            try {
                model.delete(bean);
                ServletUtility.redirect(NGOView.USER_LIST_CTL, request,
                        response);
                return;
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
              
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
        	ServletUtility.redirect(NGOView.USER_LIST_CTL, request, response);
        	
        }else if (OP_RESET.equalsIgnoreCase(op)) {
    		ServletUtility.redirect(NGOView.USER_CTL, request, response);
    		return;
    }
    				
    		
        ServletUtility.forward(getView(), request, response);
        

        log.debug("UserCtl Method doPostEnded");
	}
	
	
	protected String SubImage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("");
		String savePath = DataUtility.getString(PropertyReader.getValue("imagePath"));

		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}

		Part part = request.getPart("photo");
		String fileName = extractFileName(part);
		part.write(savePath + File.separator + fileName);
		String filePath = fileName;
		System.out.println("Path----" + savePath + File.separator + fileName);

		return fileName;

	}
	
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
	
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		return NGOView.USER_VIEW;
	}

}
