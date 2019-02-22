package css.webappdemopagecontroller.webpresentation;

import css.webappdemopagecontroller.services.AddressDTO;

public class MoradaHelper extends Helper {
	private int id;
	private String address;
	private int vat;
	
	public void setId(int id) {
		this.id = id;	
	}

	public int getId() {
		return id;	
	}
	
	public String getAddress(){
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;	
	}
	
	
	public int getVat() {
		return vat;
	}
	
	public void setVat(int vat){
		this.vat = vat;
	}
	
	public void fillWithAddress(AddressDTO addressDTO) {
		id = addressDTO.id;
		address = addressDTO.address;
		vat = addressDTO.vat;
	}
}
