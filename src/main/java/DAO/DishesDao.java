package DAO;


import model.Client;
import model.Dishes;
import persistant.ConnectionManager;
import transformer.ClientTransformer;
import transformer.DishTransformer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishesDao implements  InterfaceDAO<Dishes> {
    private final String GET_DISHES_BY_ID = "SELECT dish_id, dish_name,dish_amount,dish_price FROM dishes WHERE dish_id = ?";
    private final String INSERT_NEW_DISH = "INSERT INTO dishes (dish_id, dish_name,dish_amount,dish_price) VALUES (?,?, ?,?)";
    private final String UPDATE_DISH = "UPDATE dishes SET dish_name = 'Sousage' WHERE id = ?";
    private final String DELETE_DISH = "DELETE FROM dishes WHERE dish_id = ?";
    private final String GET_ALL_DISHES = "SELECT * FROM dishes";
    public void create(Dishes model) throws SQLException {
        Connection conn = ConnectionManager.getConnection();

        PreparedStatement stmt = conn.prepareStatement(INSERT_NEW_DISH);

        stmt.setInt(1, model.getDish_id());
        stmt.setString(2, model.getDish_name());
        stmt.setDouble(3, model.getDish_amount());
        stmt.setDouble(4, model.getDish_price());

        stmt.executeUpdate();
    }

    public void update(Dishes model) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement(UPDATE_DISH);
        stmt.setInt(1, model.getDish_id());
        stmt.executeUpdate();
    }

    public void delete(Dishes model) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement(DELETE_DISH);
        stmt.setInt(1, model.getDish_id());
        stmt.executeUpdate();
    }

    public Dishes getDishesById(Integer id) throws SQLException {
        Dishes result = null;

        Connection conn = ConnectionManager.getConnection();

        PreparedStatement stmt = conn.prepareStatement(GET_DISHES_BY_ID);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        result = new DishTransformer().fromResultSetToObject(rs);

        return result;
    }
    public List<Dishes> getAll() throws SQLException {

        List<Dishes> listDishes = new ArrayList<Dishes>();

        Connection connection = ConnectionManager.getConnection();

        try (PreparedStatement stm = connection.prepareStatement(GET_ALL_DISHES)) {
            ResultSet rs = stm.executeQuery();

            while (!rs.isLast()) {
                listDishes.add(new DishTransformer().fromResultSetToObject(rs));
            }
        }
        return listDishes;
    }


}
