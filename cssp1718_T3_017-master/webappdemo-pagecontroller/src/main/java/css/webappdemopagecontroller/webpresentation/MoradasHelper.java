package css.webappdemopagecontroller.webpresentation;

import java.util.ArrayList;
import java.util.List;

import css.webappdemopagecontroller.services.AddressDTO;
import css.webappdemopagecontroller.services.ListAddressDTO;

public class MoradasHelper extends Helper {
	
	private List<MoradaHelper> listAddress;


	public List<MoradaHelper> getListAddress() {
		return listAddress;
	}


	public void fillWithAddresses(ListAddressDTO addressDTO) {
		this.listAddress =  new ArrayList<>();
		for (AddressDTO address : addressDTO.getListAddress()) {
			MoradaHelper helper = new MoradaHelper();
			helper.fillWithAddress(address);
			this.listAddress.add(helper);
		}
	}

}
