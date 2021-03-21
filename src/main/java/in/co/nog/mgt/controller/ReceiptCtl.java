package in.co.nog.mgt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.nog.mgt.bean.DonorMoneyBean;
import in.co.nog.mgt.bean.UserBean;
import in.co.nog.mgt.exception.ApplicationException;
import in.co.nog.mgt.model.DonorMoneyModel;
import in.co.nog.mgt.model.UserModel;
import in.co.nog.mgt.util.DataUtility;
import in.co.nog.mgt.util.ServletUtility;

 
@WebServlet(name = "ReceiptCtl", urlPatterns = { "/ctl/receipt" })
public class ReceiptCtl extends BaseCtl {
	
	private static final long serialVersionUID = 1L;

	

	/**
	 * Contains display logic
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get model
        
		DonorMoneyModel model = new DonorMoneyModel();
        
		long id = DataUtility.getLong(request.getParameter("id"));
       
		if (id > 0 ) {
          
            
			DonorMoneyBean bean;
            try {
                bean = model.findByPK(id);
                ServletUtility.setBean(bean, request);
            } catch (ApplicationException e) {
                ServletUtility.handleException(e, request, response);
                return;
            }
        }
        ServletUtility.forward(getView(), request, response);
	
	}
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return NGOView.RECEIPT_VIEW;
	}

}
