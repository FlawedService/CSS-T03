package css.webappdemopagecontroller.webpresentation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import css.webappdemopagecontroller.services.ApplicationException;
import css.webappdemopagecontroller.services.SaleService;

@WebServlet("/CloseSalePageController")
public class CloseSalePageController extends PageController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SaleService ss = SaleService.INSTANCE;

		SaleHelper sh = new SaleHelper();
		request.setAttribute("SaleHelper", sh);
		try{
			
			String customerId = request.getParameter("customerId");
			//String id = request.getParameter("id");
			
			if (isInt(sh, customerId, " Vat nao valido ")){
				int customerNmber = intValue(customerId);
				//int idNumber = intValue(id);
				
				ss.CloseSale(customerNmber);
				sh.fillWithSale(ss.getSaleById(customerNmber));
				
				request.getRequestDispatcher("CloseSaleInfo.jsp").forward(request, response);
			}else {
				sh.addMessage("Not possible ");
				request.getRequestDispatcher("SaleError.jsp").forward(request, response);
			}
		}catch (ApplicationException e){
			sh.addMessage("Not possible " + e.getMessage());
			request.getRequestDispatcher("SaleError.jsp").forward(request, response);
		}
	}

	
}
