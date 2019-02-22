package css.webappdemopagecontroller.services;

import java.util.Set;

public class ListSaleDTO {

	public Set<SaleDTO> listSale;
	
	
	
	public ListSaleDTO(Set<SaleDTO> listSale) {
		this.listSale = listSale;
	}
	
	public Set<SaleDTO> getListSale() {
		return listSale;
	}
}
