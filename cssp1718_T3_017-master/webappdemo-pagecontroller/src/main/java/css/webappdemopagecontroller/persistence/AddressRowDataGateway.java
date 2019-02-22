package css.webappdemopagecontroller.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


/**
 * An in-memory representation of a Address table record. 
 *	
 * @author Css 017
 * 
 *
 */
public class AddressRowDataGateway {

	// Address attributes 

	/**
	 * 
	 * 
	 */
	private int id;

	/**
	 * Customer's VAT number
	 */
	private int vat;

	/**
	 * 
	 */
	private String address;




	// 1. constructors

	//FOR TEST PURPOSE ONLY
	public AddressRowDataGateway(int vat, String address) {
		//this.id = id;
		this.vat = vat;
		this.address = address;
	}


	public AddressRowDataGateway(ResultSet rs) throws RecordNotFoundException {
		try {
			fillAttributes(rs.getInt("vatNumber"),  rs.getString("address"));
			this.id = rs.getInt("id");
		} catch (SQLException e) {
			throw new RecordNotFoundException ("Address does not exist", e);
		}
	}

	private void fillAttributes(int vat, String address) {
		this.vat = vat;
		this.address = address;
	}


	// 2. getters and setters

	/**
	 * @return 
	 */
	public int getAddressId() {
		return id;
	}




	/**
	 * @return  
	 */
	public int getVat() {
		return vat;
	}

	/**
	 * @return 
	 */
	public String getAddress() {
		return address;
	}








	/**
	 * The insert customer SQL statement
	 */


	private static final String INSERT_ADDRESS_SQL = 
			"insert into residence (id, place, customer_id) " +
					"values (DEFAULT, ?, ?)";

	public void insert() throws PersistenceException {
		try (PreparedStatement statement = DataSource.INSTANCE.prepareGetGenKey(INSERT_ADDRESS_SQL)) {
			// set statement arguments
			statement.setString(1, address);
			statement.setInt(2, vat);
			// executes SQL
			statement.executeUpdate();
			// Gets address Id generated automatically by the database engine
			try (ResultSet rs = statement.getGeneratedKeys()) {
				rs.next(); 
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new PersistenceException ("Internal error!", e);
		}


	}
	private static final String FIND_ADDRESSES_SQL = "select a from residence"
			+ " where customer_id = ?";
	


	public static Set<AddressRowDataGateway> getAllAddressByVat (int vat) throws PersistenceException {
		try(PreparedStatement statement = DataSource.INSTANCE.prepareGetGenKey(FIND_ADDRESSES_SQL)){

			statement.setInt(1, vat);

			try(ResultSet rs = statement.executeQuery()){
				return loadAllAddress(rs);
			}
		}catch(SQLException e){
			throw new PersistenceException("Internal error");
		}
	}



	private static Set<AddressRowDataGateway> loadAllAddress(ResultSet rs) throws SQLException{
		Set<AddressRowDataGateway> result = new HashSet<AddressRowDataGateway>();
		while(rs.next()){
			AddressRowDataGateway address = new AddressRowDataGateway(rs.getInt("customer_id"), rs.getString("place"));
			address.id = rs.getInt("id");
			result.add(address);
		}
		return result;
	}






}
