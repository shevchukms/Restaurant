package DAO;

import model.AbstractModel;

import java.sql.SQLException;

/**
 * Created by Mykola on 07.09.2017.
 */

public interface InterfaceDAO <T extends AbstractModel> {

    void create(T model) throws SQLException;
    void update(T model) throws SQLException;
    void delete(T model) throws SQLException;

}
