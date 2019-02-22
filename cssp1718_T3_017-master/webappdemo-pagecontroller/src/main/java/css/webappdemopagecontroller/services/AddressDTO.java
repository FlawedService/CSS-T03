	package css.webappdemopagecontroller.services;

public class AddressDTO {

	/**
	 * 
	 */
	public final int id;

	/**
	 * 
	 */
	public final int vat;

	/**
	 *  
	 */
	public final String address;
	


	public AddressDTO(int id, int vat, String address) {
		this.id = id;
		this.vat = vat;
		this.address = address;
	}
	
}
