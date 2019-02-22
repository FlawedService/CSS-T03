package css.webappdemopagecontroller.webpresentation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import css.webappdemopagecontroller.services.ApplicationException;
import css.webappdemopagecontroller.services.CustomerService;

@WebServlet("/InsertCustomerPageController")
public class InsertCustomerPageController extends PageController {

	private static final long serialVersionUID = 1L;

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CustomerService cs = CustomerService.INSTANCE;
		//MoradaHelper mh = new MoradaHelper();
		CustomerHelper ch = new CustomerHelper();
		request.setAttribute("helper", ch);
		try{
			
			String vat = request.getParameter("vat");
			String designation = request.getParameter("designation");
			String phone = request.getParameter("phone");
			//String address = request.getParameter("address");
			if (isInt(ch, vat, " Vat nao valido ")){
				int vatNmber = intValue(vat);
				int phoneNumber = intValue(phone);
		
				cs.addNewCustomer(vatNmber, designation, phoneNumber);
				ch.fillWithCustomer(cs.getCustomerByVat(vatNmber));
				//mh.fillWithAddress(cs.addAddress(vatNmber, address));
				request.getRequestDispatcher("CustomerInfo.jsp").forward(request, response);
			}
		}catch (ApplicationException e){
			ch.addMessage("Not possible " + e.getMessage());
			request.getRequestDispatcher("CustomerError.jsp").forward(request, response);
		}



}
}

