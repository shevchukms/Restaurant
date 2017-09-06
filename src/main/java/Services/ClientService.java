package Services;

import DAO.ClientDAO;
import model.Client;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mykola on 07.09.2017.
 */
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

    public Client getStudentById(Integer id) throws SQLException {
        return new ClientDAO().getStudentById(id);
    }

    public List<Client> getAllStudent() throws SQLException {
        return new ClientDAO().getAll();
    }
}
