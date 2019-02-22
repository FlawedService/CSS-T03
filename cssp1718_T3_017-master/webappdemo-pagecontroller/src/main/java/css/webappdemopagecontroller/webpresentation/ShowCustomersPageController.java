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

@WebServlet("/ShowCustomersPageController")
public class ShowCustomersPageController extends PageController {
	private static final long serialVersionUID = 1L;

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CustomerService cs = CustomerService.INSTANCE;

		CustomersHelper ch = new CustomersHelper();
		request.setAttribute("helper", ch);
		try{
			ch.fillWithCustomers(cs.getallCustomers());
		
			request.getRequestDispatcher("ShowCustomer.jsp").forward(request, response);
		}catch ( ApplicationException | PersistenceException | SQLException e) {
			ch.addMessage("It was not possible to fufill the request: ");
			request.getRequestDispatcher("CustomerError.jsp").forward(request, response); 
		}
	}
}
