package css.webappdemopagecontroller.services;

import java.util.Set;

public class ListAddressDTO {
	
public  Set<AddressDTO> listAddress;
	
	
	
	public ListAddressDTO(Set<AddressDTO> listAddress) {
		this.listAddress = listAddress;
	}
	
	public Set<AddressDTO> getListAddress() {
		return listAddress;
	}

}
