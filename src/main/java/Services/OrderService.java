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

    public void deleteOrderByClientID(Integer id) throws SQLException {
        new OrderDAO().deleteOrderByClentId(id);
    }

    public Order getOrdersById(Integer id) throws SQLException {
        return new OrderDAO().getOrderById(id);
    }

    public List<Order> getAllOrders() throws SQLException {
        return new OrderDAO().getAll();
    }

    public Double getBillByClientID(Integer id) throws SQLException {
        return new OrderDAO().getBillById(id);
    }

}
