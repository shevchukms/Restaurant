package transformer;

import model.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Mykola on 07.09.2017.
 */
public class ClientTransformer  {
    public Client fromResultSetToObject(ResultSet rs) throws SQLException {

        Client result = null;

        if (rs.next()) {
            result = new Client();
            result.setId(rs.getInt("client_id"));
            result.setName(rs.getString("client_name"));
            result.setTableNumber(rs.getInt("client_number_of_table"));

        }
        return result;
    }
}
