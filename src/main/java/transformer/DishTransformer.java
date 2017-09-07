package transformer;

import model.Client;
import model.Dishes;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DishTransformer {
    public Dishes fromResultSetToObject(ResultSet rs) throws SQLException {

        Dishes result = null;

        if (rs.next()) {
            result = new Dishes();
            result.setDish_id(rs.getInt("dish_id"));
            result.setDish_name(rs.getString("dish_name"));
            result.setDish_amount(rs.getDouble("dish_amount"));
            result.setDish_price(rs.getDouble("dish_price"));
        }
        return result;
    }
}
