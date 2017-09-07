package DAO;

import model.Order;
import persistant.ConnectionManager;
import transformer.OrderTransformer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mykola on 07.09.2017.
 */
public class OrderDAO implements InterfaceDAO<Order> {

    private final String GET_ORDERS_BY_ID = "SELECT dish_id, dish_name,dish_amount,dish_price FROM dishes WHERE dish_id = ?";
    private final String INSERT_NEW_ORDER = "INSERT INTO dishes (dish_id, dish_name,dish_amount,dish_price) VALUES (?,?, ?,?)";
    private final String UPDATE_ORDER = "UPDATE dishes SET dish_name = 'Sousage' WHERE id = ?";
    private final String DELETE_ORDER = "DELETE FROM dishes WHERE dish_id = ?";
    private final String GET_ALL_ORDERS = "SELECT * FROM dishes";

    @Override
    public void create(Order model) throws SQLException {
        Connection conn = ConnectionManager.getConnection();

        PreparedStatement stmt = conn.prepareStatement(INSERT_NEW_ORDER);

        stmt.setInt(1, model.getId());
        stmt.setInt(2, model.getClient_id());
        stmt.setInt(3, model.getDish_id());

        stmt.executeUpdate();
    }

    @Override
    public void update(Order model) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement(UPDATE_ORDER);
        stmt.setInt(1, model.getId());
        stmt.executeUpdate();
    }

    @Override
    public void delete(Order model) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement(DELETE_ORDER);
        stmt.setInt(1, model.getId());
        stmt.executeUpdate();
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
