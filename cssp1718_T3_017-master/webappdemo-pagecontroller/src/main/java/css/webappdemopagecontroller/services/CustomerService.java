package css.webappdemopagecontroller.services;

import java.sql.SQLException;

import css.webappdemopagecontroller.persistence.AddressRowDataGateway;
import css.webappdemopagecontroller.persistence.CustomerFinder;
import css.webappdemopagecontroller.persistence.CustomerRowDataGateway;
import css.webappdemopagecontroller.persistence.PersistenceException;
import css.webappdemopagecontroller.webpresentation.MoradaHelper;


/**
 * Handles customer transactions. 
 * Each public method implements a transaction script.
 * 
 * @author 
 * @version
 *
 */
public enum CustomerService {
	INSTANCE;
	
	public CustomerDTO getCustomerByVat (int vat) throws ApplicationException {
		if (!isValidVAT (vat))
			throw new ApplicationException ("Invalid VAT number: " + vat);
		else try {
			CustomerRowDataGateway customer = new CustomerFinder().getCustomerByVATNumber(vat);
			return new CustomerDTO(customer.getCustomerId(), customer.getVAT(), 
					customer.getDesignation(), customer.getPhoneNumber());
		} catch (PersistenceException e) {
				throw new ApplicationException ("Customer with vat number " + vat + " not found.", e);
		}
	}
	
	public AddressDTO addAddress(int vat, String address) throws ApplicationException {
		if(!isValidVAT(vat))
			throw new ApplicationException("Invalid VAT number: " + vat);
		else try {
			AddressRowDataGateway CustomerAddress = new AddressRowDataGateway(vat, address);
			CustomerAddress.insert();
			return new AddressDTO(CustomerAddress.getAddressId(),CustomerAddress.getVat(),CustomerAddress.getAddress());
			
		}catch (PersistenceException e) {
			throw new ApplicationException("Address "+ address +" to client with vat number " + vat + " not found.", e);
		}
	}
	
	public void updateCustomerPhone(int vat, int phoneNumber) throws ApplicationException {
		if (!isValidVAT (vat))
			throw new ApplicationException ("Invalid VAT number: " + vat);
		else try {
			CustomerRowDataGateway customer = new CustomerFinder().getCustomerByVATNumber(vat);
			customer.setPhoneNumber(phoneNumber);
			customer.updatePhoneNumber();
		} catch (PersistenceException e) {
				throw new ApplicationException ("Customer with vat number " + vat + " not found.", e);
		}
	}
	
	public void removeClient(int vat) throws ApplicationException {
		if (!isValidVAT (vat))
			throw new ApplicationException ("Invalid VAT number: " + vat);
		else try {
			CustomerRowDataGateway customer = new CustomerFinder().getCustomerByVATNumber(vat);
			customer.RemoveCustomer();
		} catch (PersistenceException e) {
				throw new ApplicationException ("Remove Customer with vat number " + vat + " not found.", e);
		}
	}
	
	
	public void addNewCustomer(int vat, String designation, int phoneNumber) throws ApplicationException {
		if(!isValidVAT(vat))
			throw new ApplicationException("Invalid VAT number: " + vat);
		else try {
			CustomerRowDataGateway customer = new CustomerRowDataGateway(vat, designation, phoneNumber);
			customer.insert();
		}catch (PersistenceException e) {
			throw new ApplicationException("error ", e);
		}
		
		
	}
	public CustomerDTO getCustomerById(int id) throws ApplicationException{
		if (!isValidVAT (id))
			throw new ApplicationException ("Invalid Id number: " + id);
		else try {
			CustomerRowDataGateway customer = new CustomerFinder().getCustomerById(id);
			return new CustomerDTO(customer.getCustomerId(), customer.getVAT(), 
					customer.getDesignation(), customer.getPhoneNumber());
		} catch (PersistenceException e) {
				throw new ApplicationException ("Customer with id number " + id + " not found.", e);
		}
	}
	
	public CustomersDTO getallCustomers() throws ApplicationException, PersistenceException, SQLException{
		return new CustomersDTO( new CustomerFinder().showAllCustomers());
		
	}
	
	public ListAddressDTO getAllAddressByVat(int vat) throws PersistenceException {
		return new ListAddressDTO(new CustomerFinder().getAllAddress(vat));
		
	}

	 
	/**
	 * Checks if a VAT number is valid.
	 * 
	 * @param vat The number to be checked.
	 * @return Whether the VAT number is valid. 
	 */
	private boolean isValidVAT(int vat) {
		// If the number of digits is not 9, error!
		if (vat < 100000000 || vat > 999999999)
			return false;
		
		// If the first number is not 1, 2, 5, 6, 8, 9, error!
		int firstDigit = vat / 100000000;
		if (firstDigit != 1 && firstDigit != 2 && 
			firstDigit != 5 && firstDigit != 6 &&
			firstDigit != 8 && firstDigit != 9)
			return false;
		
		// Checks the congruence modules 11.
		int sum = 0;
		int checkDigit = vat % 10;
		vat /= 10;
		
		for (int i = 2; i < 10 && vat != 0; i++) {
			sum += vat % 10 * i;
			vat /= 10;
		}
		
		int checkDigitCalc = 11 - sum % 11;
		if (checkDigitCalc == 10)
			checkDigitCalc = 0;
		return checkDigit == checkDigitCalc;
	}


}
