package css.webappdemopagecontroller.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import css.webappdemopagecontroller.services.CustomerDTO;
import css.webappdemopagecontroller.services.SaleDTO;

public class SaleFinder {
	
	private static final String GET_SALE_BY_VAT_SQL = 
			"select * from sale where customer_id = ?";
	
	public SaleRowDataGateway getSaleByVat (int vatNumber) throws PersistenceException {
		try (PreparedStatement statement = DataSource.INSTANCE.prepare(GET_SALE_BY_VAT_SQL)){
			statement.setInt(1, vatNumber);
			try (ResultSet rs = statement.executeQuery()) {
				rs.next();
				return new SaleRowDataGateway(rs);
			}
		} catch (SQLException e) {
			throw new PersistenceException("Internal error cannot get sale with vat "+ vatNumber , e);
		}
	}


}
