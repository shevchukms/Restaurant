package persistant;

/**
 * Created by Mykola on 07.09.2017.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionManager {
    private static String jdbcUrl = "jdbc:mysql://localhost:3306/restaurant";
    private static String user = "root";
    private static String password = "root";

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            initializeConnection();
        }
        return connection;
    }

    private static void initializeConnection() {

		/*try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}*/
        try {
            connection = DriverManager.getConnection(jdbcUrl, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.setAutoCommit(false);
            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            // Операція 1
            String query1 = "UPDATE clients SET client_name = 'CLIENT' WHERE id = ?";
            PreparedStatement pst1 = connection.prepareStatement(query1);
            pst1.setInt(1, 1);
            pst1.executeUpdate();

            // Операція 2
            String query2 = "UPDATE dishes SET dish_name = 'DISH' WHERE id = ?";
            PreparedStatement pst2 = connection.prepareStatement(query2);
            pst2.setInt(2, 1);
            pst2.executeUpdate();
            connection.commit();
        } catch (SQLException e1) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
