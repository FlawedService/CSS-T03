package css.webappdemopagecontroller.webpresentation;

import java.util.Date;

import css.webappdemopagecontroller.persistence.SaleStatus;
import css.webappdemopagecontroller.services.SaleDTO;

public class SaleHelper extends Helper {
	
	
	private int customerId;

	private Date date;
	
	private SaleStatus status;

	private int id;
	
	private double total;

	
	
	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}
	
	public double getTotal(){
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public SaleStatus getStatus() {
		return status;
	}
	
	public void setStatus(SaleStatus status) {
		this.status = status;
	}

	
	public void fillWithSale(SaleDTO SaleDTO) {
		id = SaleDTO.getId();
		customerId = SaleDTO.getCustomerId();
		date = SaleDTO.getDate();
		status = SaleDTO.getStatus();
		
	}




}
