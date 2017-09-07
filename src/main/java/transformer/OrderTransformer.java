package transformer;

import model.Dishes;
import model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Mykola on 07.09.2017.
 */
public class OrderTransformer {
    public Order fromResultSetToObject(ResultSet rs) throws SQLException {

        Order result = null;

        if (rs.next()) {
            result = new Order();
            result.setId(rs.getInt("order_id"));
            result.setClient_id(rs.getInt("client_id"));
            result.setDish_id(rs.getInt("dish_id"));

        }
        return result;
    }
}
