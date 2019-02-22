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
 * Servlet implementation class AddAddressPageController
 */
@WebServlet("/AddAddressPageController")
public class AddAddressPageController extends PageController {

	private static final long serialVersionUID = 1L;

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerService cs = CustomerService.INSTANCE;        

		MoradaHelper mh = new MoradaHelper();
		request.setAttribute("helperAddress", mh);
		try {
			String vat = request.getParameter("vat");
			String address = request.getParameter("address");
			
			if (isInt(mh, vat, "Invalid VAT number")) {
				int vatNumber = intValue(vat);
				cs.addAddress(vatNumber, address);
				
				mh.fillWithAddress(cs.addAddress(vatNumber, address));
				request.getRequestDispatcher("AddressInfo.jsp").forward(request, response);
			}
		} catch (ApplicationException e) {
			mh.addMessage("It was not possible to fulfill the request: " + e.getMessage());
			request.getRequestDispatcher("CustomerError.jsp").forward(request, response); 
		} 

	}

}
