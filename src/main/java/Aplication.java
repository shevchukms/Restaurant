import DAO.OrderDAO;
import Services.ClientService;
import Services.DishService;
import Services.OrderService;
import model.Client;
import model.Dishes;
import model.Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mykola on 07.09.2017.
 */
public class Aplication {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String selected;

    public Aplication() throws SQLException, IOException {

        ClientsTable();
        DishesTable();
        work();
    }

    public void DishesTable() throws SQLException {
        Dishes dish = new Dishes();
        DishService dishDAO = new DishService();


        dish.setDish_amount(Double.valueOf(2));
        dish.setDish_price(Double.valueOf(25));
        dish.setDish_id(25);
        dish.setDish_name("Holodets");
        new DishService().create(dish);
        List<Dishes> dishes = dishDAO.getAllDishes();

        for (Dishes d : dishes) {
            System.out.println(d.toString());
        }

    }

    public void ClientsTable() throws SQLException {
        Client client = new Client();
        ClientService clientDAO = new ClientService();

        client.setId(2);
        client.setName("Petro");
        client.setTableNumber(3);
        new ClientService().create(client);
        List<Client> clients = clientDAO.getAllClients();

        for (Client cl : clients) {
            System.out.println(cl.toString());
        }

    }


    public void work() throws IOException, SQLException {
        boolean flag = true;


        while (flag) {
            System.out.println("***************************");
            System.out.println("press 1 - To take a sit");
            System.out.println("press 2 - Make order");
            System.out.println("press 3 - ask for an invoice ");
            System.out.println("press 0 - Exit");

            Client client = new Client();
            List<Dishes> dishes = new ArrayList<>();

            selected = br.readLine();
            switch (selected) {
                case "1":
                    fillClientFilds(client);
                    new ClientService().create(client);
                    break;
                case "2":


                    break;
                case "3":

                    break;

                case "0":
                    flag = false;
                    break;


                default:
                    break;


            }
        }


    }

    public void fillClientFilds(Client client) throws IOException {

        System.out.println("What is your name?");
        selected = br.readLine();
        client.setName(selected);
        System.out.println("What is number of yours Table?");
        selected = br.readLine();
        client.setTableNumber(Integer.parseInt(selected));
        client.setId(client.hashCode());
    }

    public ArrayList<Dishes> selectDishes() throws SQLException, IOException {
        ArrayList<Dishes> selectedDishes = new ArrayList<>();
        DishService dishDAO = new DishService();

        boolean flag = true;

        while (flag) {
            System.out.println("***************************");
            System.out.println("Write KODE OF DISH or 0 to finish");
            List<Dishes> dishesFromDataBase = dishDAO.getAllDishes();
            selected = br.readLine();
            if (Integer.parseInt(selected) == 0) {
                flag = false;
            } else {

                selectDishes().add(dishDAO.getDishesById(Integer.parseInt(selected)));
            }
        }
        return selectedDishes;
    }

    public void makeOrder(Client client, ArrayList<Dishes> dishes) {
        Order order=new Order();
        OrderDAO orderDAO = new OrderService();


        dish.setDish_amount(Double.valueOf(2));
        dish.setDish_price(Double.valueOf(25));
        dish.setDish_id(25);
        dish.setDish_name("Holodets");
        new DishService().create(dish);
        List<Dishes> dishes = dishDAO.getAllDishes();

        for (Dishes d : dishes) {
            System.out.println(d.toString());
        }

    }
}
