package css.webappdemopagecontroller.webpresentation;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import css.webappdemopagecontroller.persistence.PersistenceException;
import css.webappdemopagecontroller.services.ApplicationException;
import css.webappdemopagecontroller.services.CustomerService;

/**
 * Servlet implementation class RemoveCustomerPageController
 */
@WebServlet("/RemoveCustomerPageController")
public class RemoveCustomerPageController extends PageController {
	
	private static final long serialVersionUID = 1L;


	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerService cs = CustomerService.INSTANCE;        

		CustomerHelper ch = new CustomerHelper();
		request.setAttribute("helper", ch);
		try{
			String vat = request.getParameter("vat");
			if (isInt(ch, vat, "Invalid VAT number")) {
				int vatNumber = intValue(vat);
				
				
				ch.fillWithCustomer(cs.getCustomerByVat(vatNumber));
				cs.removeClient(vatNumber);
				
				request.getRequestDispatcher("RemoveCustomerInfo.jsp").forward(request, response);
			}
		} catch (ApplicationException e) {
			ch.addMessage("It was not possible to fulfill the request: " + e.getMessage());
			request.getRequestDispatcher("CustomerError.jsp").forward(request, response); 
		}
	}


}
