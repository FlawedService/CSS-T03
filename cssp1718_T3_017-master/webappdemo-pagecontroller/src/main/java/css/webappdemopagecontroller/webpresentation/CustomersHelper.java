package css.webappdemopagecontroller.webpresentation;

import java.util.ArrayList;
import java.util.List;

import css.webappdemopagecontroller.services.CustomerDTO;
import css.webappdemopagecontroller.services.CustomersDTO;

public class CustomersHelper extends Helper {

	private List<CustomerHelper> listClients;


	public List<CustomerHelper> getListClients() {
		return listClients;
	}


	public void fillWithCustomers(CustomersDTO customersDTO) {
		this.listClients =  new ArrayList<>();
		for (CustomerDTO customer : customersDTO.getListClient()) {
			CustomerHelper helper = new CustomerHelper();
			helper.fillWithCustomer(customer);
			this.listClients.add(helper);
		}
	}
}	
