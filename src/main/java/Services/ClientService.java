package Services;

import DAO.ClientDAO;
import model.Client;

import java.sql.SQLException;
import java.util.List;


public class ClientService {
    public void create(Client client ) throws SQLException {
        new ClientDAO().create(client);
    }

    public void update(Client client ) throws SQLException {
        new ClientDAO().update(client);
    }

    public void delete(Client client ) throws SQLException {
        new ClientDAO().delete(client);
    }

    public Client getClientById(Integer id) throws SQLException {
        return new ClientDAO().getStudentById(id);
    }

    public List<Client> getAllClients() throws SQLException {
        return new ClientDAO().getAll();
    }
}
