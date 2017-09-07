package DAO;

import model.Order;
import persistant.ConnectionManager;
import transformer.DoubleTransformer;
import transformer.OrderTransformer;
import transformer.UniversalTransformer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OrderDAO implements InterfaceDAO<Order> {

    private final String GET_ORDERS_BY_ID = "SELECT dish_id, dish_name,dish_amount,dish_price FROM orders WHERE dish_id = ?";
    private final String GET_SCORE_BY_ID = "SELECT sum(dish_price)as sum from orders o join dishes d on d.dish_id=o.dish_id where o.client_id=?";
    private final String INSERT_NEW_ORDER = "INSERT INTO orders (client_id , dish_id ) VALUES ( ?,?)";
    private final String UPDATE_ORDER = "UPDATE orders SET dish_name = 'Sousage' WHERE id = ?";
    private final String DELETE_ORDER = "DELETE FROM orders WHERE order_id  = ?";
    private final String GET_ALL_ORDERS = "SELECT * FROM orders";


    @Override
    public void create(Order model) throws SQLException {
        Connection conn = ConnectionManager.getConnection();

        PreparedStatement stmt = conn.prepareStatement(INSERT_NEW_ORDER);

       // stmt.setInt(1, model.getId());
        stmt.setInt(1, model.getClient_id());
        stmt.setInt(2, model.getDish_id());

        stmt.executeUpdate();        conn.commit();

    }

    @Override
    public void update(Order model) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement(UPDATE_ORDER);
        stmt.setInt(1, model.getId());
        stmt.executeUpdate();        conn.commit();

    }

    @Override
    public void delete(Order model) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement(DELETE_ORDER);
        stmt.setInt(1, model.getId());
        stmt.executeUpdate();        conn.commit();

    }
    public Double getScoreById(Integer id) throws SQLException {
        Double result = null;
        Connection conn = ConnectionManager.getConnection();

        PreparedStatement stmt = conn.prepareStatement(GET_SCORE_BY_ID);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        result = new DoubleTransformer().fromResultSetToObject(rs);
        return result;
    }
    public Order getDishesById(Integer id) throws SQLException {
        Order result = null;

        Connection conn = ConnectionManager.getConnection();

        PreparedStatement stmt = conn.prepareStatement(GET_ORDERS_BY_ID);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        result = new OrderTransformer().fromResultSetToObject(rs);

        return result;
    }

    public List<Order> getAll() throws SQLException {

        List<Order> listDishes = new ArrayList<Order>();

        Connection connection = ConnectionManager.getConnection();

        try (PreparedStatement stm = connection.prepareStatement(GET_ALL_ORDERS)) {
            ResultSet rs = stm.executeQuery();

            while (!rs.isLast()) {
                listDishes.add(new OrderTransformer().fromResultSetToObject(rs));
            }
        }
        return listDishes;
    }


}
