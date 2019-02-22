	package css.webappdemopagecontroller.webpresentation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import css.webappdemopagecontroller.persistence.PersistenceException;
import css.webappdemopagecontroller.services.ApplicationException;
import css.webappdemopagecontroller.services.CustomerService;


/**
 * Servlet implementation class GetCustomerPageController
 * 
 * Handles the http get and post. Decides which model and view to use.
 * 
 * Decode the URL, extract any form data, decide action 
 * Create and invoke any model objects
 * Determine which view should display the result page 
 * (Forward information to it)
 * 
 */
@WebServlet("/GetCustomerPageController")
public class GetCustomerPageController extends PageController {

	private static final long serialVersionUID = 1L;

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerService cs = CustomerService.INSTANCE;       
		

		CustomerHelper ch = new CustomerHelper();
		MoradasHelper mh = new MoradasHelper();
		request.setAttribute("helper", ch);
		request.setAttribute("helperAddress", mh);
		try {
			String vat = request.getParameter("vat");	
			if (isInt(ch, vat, "Invalid VAT number")) {
				int vatNumber = intValue(vat);
				ch.fillWithCustomer(cs.getCustomerByVat(vatNumber));
				mh.fillWithAddresses(cs.getAllAddressByVat(vatNumber));
				request.getRequestDispatcher("CustomerInfo.jsp").forward(request, response);
			}
		} catch (ApplicationException e) {
			ch.addMessage("It was not possible to fulfill the request: " + e.getMessage());
			request.getRequestDispatcher("CustomerError.jsp").forward(request, response); 
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
