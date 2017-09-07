package Services;


import DAO.DishesDao;
import model.Dishes;

import java.sql.SQLException;
import java.util.List;


public class DishService {


    public void create(Dishes dish) throws SQLException {
        new DishesDao().create(dish);
    }

    public void update(Dishes dish) throws SQLException {
        new DishesDao().update(dish);
    }

    public void delete(Dishes dish) throws SQLException {
        new DishesDao().delete(dish);
    }

    public Dishes getDishesById(Integer id) throws SQLException {
        return new DishesDao().getDishesById(id);
    }

    public List<Dishes> getAllDishes() throws SQLException {
        return new DishesDao().getAll();
    }
}
