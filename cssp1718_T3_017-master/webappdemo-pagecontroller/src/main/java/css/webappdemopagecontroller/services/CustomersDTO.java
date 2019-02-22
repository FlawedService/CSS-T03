package css.webappdemopagecontroller.services;

import java.util.Set;

public class CustomersDTO {
	
	public Set<CustomerDTO> listClients;
	
	
	
	public CustomersDTO(Set<CustomerDTO> listCustomers) {
		this.listClients = listCustomers;
	}
	
	public Set<CustomerDTO> getListClient() {
		return listClients;
	}
	

}
