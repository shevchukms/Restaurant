package transformer;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DoubleTransformer {


    public Double fromResultSetToObject(ResultSet rs) throws SQLException {

        Double result = null;

        if (rs.next()) {

            result = rs.getDouble("sum");
        }
        return result;
    }
}
