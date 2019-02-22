package css.webappdemopagecontroller.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * An in-memory representation of a customer table record. 
 *	
 * @author fmartins
 * @Version 1.2 (19/02/2015)
 *
 */
public class CustomerRowDataGateway {

	// Customer attributes 

	/**
	 * Customer's internal identification (unique, primary key, sequential)
	 * Generated by the database engine.
	 */
	private int id;

	/**
	 * Customer's VAT number
	 */
	private int vat;

	/**
	 * Customer's name. In case of a company, the represents its commercial denomination 
	 */
	private String designation;

	/**
	 * Customer's contact number
	 */
	private int phoneNumber;
	


	// 1. constructors

	//FOR TEST PURPOSE ONLY
	public CustomerRowDataGateway(int vat, String designation, int phoneNumber) {
		//this.id = id;
		this.vat = vat;
		this.designation = designation;
		this.phoneNumber = phoneNumber;
	}


	public CustomerRowDataGateway(ResultSet rs) throws RecordNotFoundException {
		try {
			fillAttributes(rs.getInt("vatNumber"),  rs.getString("designation"), 
					rs.getInt("phoneNumber"));
			this.id = rs.getInt("id");
		} catch (SQLException e) {
			throw new RecordNotFoundException ("Customer does not exist", e);
		}
	}

	private void fillAttributes(int vat, String designation, int phoneNumber) {
		this.vat = vat;
		this.designation = designation;
		this.phoneNumber = phoneNumber;
	}


	// 2. getters and setters

	/**
	 * @return The id of the customer
	 */
	public int getCustomerId() {
		return id;
	}

	/**
	 * @return The customer's VAT number. 
	 */
	public int getVAT() {
		return vat;
	}

	/**
	 * @return The customer's designation.
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @return The customer's phone number
	 */
	public int getPhoneNumber() {
		return phoneNumber;
	}


	/**
	 * Updates the customer designation.
	 * 
	 * @param designation The new designation to change to.
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}


	/**
	 * Updates the phone number
	 * 
	 * @param phoneNumber The new phone number
	 */
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	/**
	 * The update customerPhone SQL statement
	 */
	private static final String	UPDATE_PHONE_SQL =
			"update customer " +
					"set phonenumber = ? " +
					"where id = ?";

	public void updatePhoneNumber () throws PersistenceException {
		try (PreparedStatement statement = DataSource.INSTANCE.prepare(UPDATE_PHONE_SQL)){
			// set statement arguments
			statement.setInt(1, phoneNumber);
			statement.setInt(2, id);
			// execute SQL
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException("Internal error updating customer " + id + ".", e);
		}
	}

	/**
	 * The insert customer SQL statement
	 */
	private static final String INSERT_CUSTOMER_SQL = 
			"insert into customer (id, vatnumber, designation, phonenumber) " +
					"values (DEFAULT, ?, ?, ?)";

	public void insert() throws PersistenceException {
		try (PreparedStatement statement = DataSource.INSTANCE.prepareGetGenKey(INSERT_CUSTOMER_SQL)) {
			// set statement arguments
			statement.setInt(1, vat);
			statement.setString(2, designation);
			statement.setInt(3, phoneNumber);
			// executes SQL
			statement.executeUpdate();
			// Gets customer Id generated automatically by the database engine
			try (ResultSet rs = statement.getGeneratedKeys()) {
				rs.next(); 
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new PersistenceException ("Internal error!", e);
		}


	}

	private static final String REMOVE_CUSTOMER_SQL =
			" delete from customer " +
					"where vatnumber = ? ";


	public void RemoveCustomer () throws PersistenceException {
		try (PreparedStatement statement = DataSource.INSTANCE.prepare(REMOVE_CUSTOMER_SQL)){
			// set statement arguments
			statement.setInt(1, vat);
			// execute SQL
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException("Internal error deleting customer " + vat + ".", e);
		}
	}
	
	
	




}
