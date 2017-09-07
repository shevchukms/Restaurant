package Services;

import DAO.OrderDAO;
import model.Order;

import java.sql.SQLException;
import java.util.List;


public class OrderService {

    public void create(Order order) throws SQLException {
        new OrderDAO().create(order);
    }

    public void update(Order order) throws SQLException {
        new OrderDAO().update(order);
    }

    public void delete(Order order) throws SQLException {
        new OrderDAO().delete(order);
    }

    public Order getOrdersById(Integer id) throws SQLException {
        return new OrderDAO().getDishesById(id);
    }

    public List<Order> getAllOrders() throws SQLException {
        return new OrderDAO().getAll();
    }

    public Double getScoreByClientID(Integer id) throws SQLException {
        return new OrderDAO().getScoreById(id);
    }

}
