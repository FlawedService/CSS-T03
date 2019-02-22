package css.webappdemopagecontroller.persistence;

import java.util.Set;

import css.webappdemopagecontroller.services.AddressDTO;
import css.webappdemopagecontroller.services.CustomerDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;


/**
 * Creates a customer row gateway by finding it from the database.
 *	
 * @author fmartins
 * @Version 1.0 (03/02/2015)
 *
 */
public class CustomerFinder {

	/**
	 * The select customer by VAT SQL statement
	 */
	private static final String GET_CUSTOMER_BY_VAT_NUMBER_SQL = 
			"select * from customer where vatnumber = ?";

	/**
	 * Gets a customer by its VAT number 
	 * 
	 * @param vat The VAT number of the customer to search for
	 * @return The result set of the query
	 * @throws PersistenceException When there is an error getting the customer
	 * from the database.
	 */
	public CustomerRowDataGateway getCustomerByVATNumber (int vat) throws PersistenceException {
		try (PreparedStatement statement = DataSource.INSTANCE.prepare(GET_CUSTOMER_BY_VAT_NUMBER_SQL)){
			statement.setInt(1, vat);
			try (ResultSet rs = statement.executeQuery()) {
				rs.next();
				return new CustomerRowDataGateway(rs);
			}
		} catch (SQLException e) {
			throw new PersistenceException("Internal error getting a customer by its VAT number", e);
		}
	}

	private static final String GET_CUSTOMER_BY_ID_SQL = 
			"select * from customer where id = ?";

	public CustomerRowDataGateway getCustomerById (int id) throws PersistenceException {
		try (PreparedStatement statement = DataSource.INSTANCE.prepare(GET_CUSTOMER_BY_ID_SQL)){
			statement.setInt(1, id);
			try (ResultSet rs = statement.executeQuery()) {
				rs.next();
				return new CustomerRowDataGateway(rs);
			}
		}catch (SQLException e) {
			throw new PersistenceException("Internal error getting a customer by its VAT number", e);
		}
	}

	private static final String SHOW_CUSTOMERS_SQL = 
			"select * from customer ";

	public Set<CustomerDTO> showAllCustomers() throws PersistenceException {
		//ArrayList<CustomerRowDataGateway> customers = new ArrayList<CustomerRowDataGateway>();
		try (PreparedStatement statement = DataSource.INSTANCE.prepareGetGenKey(SHOW_CUSTOMERS_SQL)) {
			try(ResultSet rs = statement.executeQuery()){
				Set<CustomerDTO> result = new HashSet<CustomerDTO>();
				while(rs.next()) {

					CustomerDTO listcustomers = new CustomerDTO(rs.getInt("id"),rs.getInt("vatnumber"),rs.getString("designation"), rs.getInt("phonenumber"));
					result.add(listcustomers);
				}
				return result;
			}
			} catch (SQLException e) {
				throw new PersistenceException("Internal error");
			}	
		}
	
	
	private static final String FIND_ADDRESSES_SQL = "select * from residence"
			+ " where customer_id = ?";
	
	
	public Set<AddressDTO> getAllAddress (int vat) throws PersistenceException {
		try(PreparedStatement statement = DataSource.INSTANCE.prepareGetGenKey(FIND_ADDRESSES_SQL)){
			
			statement.setInt(1, vat);
			
			try(ResultSet rs = statement.executeQuery()){
				Set<AddressDTO> result = new HashSet<AddressDTO>();
				while(rs.next()) {
					
					AddressDTO listmorada = new AddressDTO(rs.getInt("id"), rs.getInt("customer_id"), rs.getString("place"));
					result.add(listmorada);
				}
				return result;
			}
		}catch(SQLException e){
			throw new PersistenceException("Internal error");
		}
	}
	
	}



