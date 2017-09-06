package DAO;

import model.AbstractModel;
import model.Client;
import persistant.ConnectionManager;
import transformer.ClientTransformer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mykola on 07.09.2017.
 */
public class ClientDAO implements  InterfaceDAO<Client> {
    private final String GET_CLIENT_BY_ID = "SELECT client_id ,client_name,client_number_of_table FROM clients WHERE client_id = ?";
    private final String INSERT_NEW_CLIENT = "INSERT INTO clients (client_id ,client_name,client_number_of_table) VALUES (?,?, ?)";
    private final String UPDATE_CLIENT = "UPDATE clients SET client_name = 'Max' WHERE id = ?";
    private final String DELETE_CLIENT = "DELETE FROM clients WHERE client_id = ?";
    private final String GET_ALL_CLIENTS = "SELECT * FROM clients";
    public void create(Client model) throws SQLException {
        Connection conn = ConnectionManager.getConnection();

        PreparedStatement stmt = conn.prepareStatement(INSERT_NEW_CLIENT);

        stmt.setInt(1, model.getId());
        stmt.setString(2, model.getName());
        stmt.setInt(3, model.getTableNumber());

        stmt.executeUpdate();
    }

    public void update(Client model) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement(UPDATE_CLIENT);
        stmt.setInt(1, model.getId());
        stmt.executeUpdate();
    }

    public void delete(Client model) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement(DELETE_CLIENT);
        stmt.setInt(1, model.getId());
        stmt.executeUpdate();
    }

    public Client getStudentById(Integer id) throws SQLException {
        Client result = null;

        Connection conn = ConnectionManager.getConnection();

        PreparedStatement stmt = conn.prepareStatement(GET_CLIENT_BY_ID);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        result = new ClientTransformer().fromResultSetToObject(rs);

        return result;
    }
    public List<Client> getAll() throws SQLException {

        List<Client> listClients = new ArrayList<Client>();

        Connection connection = ConnectionManager.getConnection();

        try (PreparedStatement stm = connection.prepareStatement(GET_ALL_CLIENTS)) {
            ResultSet rs = stm.executeQuery();

            while (!rs.isLast()) {
                listClients.add(new ClientTransformer().fromResultSetToObject(rs));
            }
        }
        return listClients;
    }

}
