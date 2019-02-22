package css.webappdemopagecontroller.webpresentation;

import java.util.ArrayList;
import java.util.List;

import css.webappdemopagecontroller.services.ListSaleDTO;
import css.webappdemopagecontroller.services.SaleDTO;

public class ListSaleHelper {
	
	private List<SaleHelper> listSale;


	public List<SaleHelper> getListSale() {
		return listSale;
	}


	public void fillWithSales(ListSaleDTO saleDTO) {
		this.listSale =  new ArrayList<>();
		for (SaleDTO sale : saleDTO.getListSale()) {
			SaleHelper helper = new SaleHelper();
			helper.fillWithSale(sale);
			this.listSale.add(helper);
		}
	}

}
