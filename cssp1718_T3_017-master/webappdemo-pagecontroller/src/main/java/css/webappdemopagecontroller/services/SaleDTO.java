package css.webappdemopagecontroller.services;

import java.util.Date;

import css.webappdemopagecontroller.persistence.SaleStatus;

public class SaleDTO {
	
	private final int id;
	
	private final int customerId;

	private final Date date;
	
	private SaleStatus status;
	

	
	
	public SaleDTO(int id, int customerId, Date data, SaleStatus status) {
		this.customerId = customerId;
		this.date = data;
		this.id = id;
		this.status = status;
	}


	public int getCustomerId() {
		return customerId;
	}


	public Date getDate() {
		return date;
	}
	
	public int getId() {
		return id;
	}

	public SaleStatus getStatus() {
		return status;
	}
	

}
