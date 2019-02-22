package css.webappdemopagecontroller.services;

import java.util.Date;

import css.webappdemopagecontroller.persistence.PersistenceException;
import css.webappdemopagecontroller.persistence.SaleFinder;
import css.webappdemopagecontroller.persistence.SaleRowDataGateway;

public enum SaleService {
	INSTANCE;


	public SaleDTO getSaleById (int customerId) throws ApplicationException {
		if(!isValidVAT(customerId)) {
			throw new ApplicationException ("Invalid Id number: " + customerId);
		}else try {
			SaleRowDataGateway sale = new SaleFinder().getSaleByVat(customerId);
			return new SaleDTO(sale.getId(), sale.getCustomerId(), sale.getDate(),sale.getStatus());
		}catch(PersistenceException e) {
			throw new ApplicationException("Sale with id not found");
		}
	}

	public int addNewSale(int customerId) throws ApplicationException {
		if(!isValidVAT(customerId))
			throw new ApplicationException("Invalid VAT number: " + customerId);
		else try {
			SaleRowDataGateway sale = new SaleRowDataGateway(customerId, new Date());
			sale.insert();
			
			return sale.getId();
		}catch (PersistenceException e) {
			throw new ApplicationException("error ", e);
		}
	}

	public void CloseSale(int customerId) throws ApplicationException {
		if(!isValidVAT(customerId))
			throw new ApplicationException("Invalid VAT number: " + customerId);
		else try {
			SaleRowDataGateway sale = new SaleFinder().getSaleByVat(customerId);
			//SaleRowDataGateway sale = new SaleRowDataGateway(customerId, new Date());
			sale.updateStatus();

		}catch (Exception e) {
			throw new ApplicationException("error on close sale", e);
		}
	}
	/*
	public ListSaleDTO getallSales() throws ApplicationException, PersistenceException, SQLException{
		return new ListSaleDTO(new SaleFinder().getAllSalesByVat());

	}*/



	private boolean isValidId(int id) {
		if(id == 0) {
			return false;
		}
		return true;
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
