package Main;

import Services.ClientService;
import Services.DishService;
import Services.OrderService;
import constants.Constants;
import model.Client;
import model.Dishes;
import model.Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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

        Constants.LOG.info("Dishes add");

        dish.setDish_amount(Double.valueOf(2));
        dish.setDish_price(Double.valueOf(25));
        dish.setDish_id(25);
        dish.setDish_name("Holodets");
        new DishService().create(dish);
        List<Dishes> dishes = dishDAO.getAllDishes();
        Constants.LOG.info("Dishes print");
        for (Dishes d : dishes) {
            System.out.println(d.toString());
        }

    }

    public void ClientsTable() throws SQLException {
        Client client = new Client();
        ClientService clientDAO = new ClientService();
        Constants.LOG.info("Client add");
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

        Client client = null;
        List<Dishes> dishes = new ArrayList<>();
        while (flag) {
            System.out.println("***************************");
            System.out.println("press 1 - To take a sit");
            System.out.println("press 2 - Make order");
            System.out.println("press 3 - ask for an bill ");
            System.out.println("press 4 - pay for bill ");
            System.out.println("press 5 - to go away ");
            System.out.println("press 0 - Exit");

            selected = br.readLine();

            switch (selected) {
                case "1":
                    client = addNewClient(client);
                    break;
                case "2":
                    Constants.LOG.info("Start makeOrder");
                    makeOrder(client, selectDishes(client));
                    break;
                case "3":
                    Constants.LOG.info("Start calculateScore");
                    calculateBill(client);
                    break;
                case "4":
                    payForBill(client);
                    break;
                case "5":
                    goAway(client);
                    break;
                case "0":
                    Constants.LOG.info("EXIT");
                    flag = false;
                    break;


                default:
                    break;

            }
        }

    }


    public Client addNewClient(Client client) throws IOException, SQLException {
        if (clientIsNull(client)) {
            client = new Client();
            Constants.LOG.info("Start fillClientFilds");
            fillClientFilds(client);
            Constants.LOG.info("Write into database New Client");
            new ClientService().create(client);
        } else {
            System.out.println("Pay for bill and go away!");
        }
        return client;
    }


    public void fillClientFilds(Client client) throws IOException {

        System.out.println("What is your name?");
        selected = br.readLine();
        client.setName(selected);
        System.out.println("What is number of yours Table?");
        selected = br.readLine();
        client.setTableNumber(Integer.parseInt(selected));
        client.setId(client.hashCode());
        Constants.LOG.info("END fillClientFilds");
    }

    public ArrayList<Dishes> selectDishes(Client client) throws SQLException, IOException {

        if (!clientIsNull(client)) {
            ArrayList<Dishes> selectedDishes = new ArrayList<>();
            DishService dishDAO = new DishService();

            boolean flag = true;

            while (flag) {
                System.out.println("***************************");
                System.out.println("Write KODE OF DISH or 0 to finish");
                List<Dishes> dishesFromDataBase = dishDAO.getAllDishes();
                for (Dishes d : dishesFromDataBase) {
                    System.out.println(d.toString());
                }


                selected = br.readLine();
                if (Integer.parseInt(selected) == 0) {
                    flag = false;
                } else {

                    selectedDishes.add(dishDAO.getDishesById(Integer.parseInt(selected)));
                }
            }
            Constants.LOG.info("END SELECT DISHES");

            return selectedDishes;
        } else {
        }
        System.out.println("Pleas, take a sit");
        return null;
    }

    public Boolean clientIsNull(Client client) {
        return client == null;
    }

    public void makeOrder(Client client, ArrayList<Dishes> dishes) throws SQLException {
        if (!clientIsNull(client)) {
            Order order = new Order();
            OrderService orderDAO = new OrderService();
            if (!dishes.isEmpty()) {
                for (Dishes dish : dishes) {

                    order.setClient_id(client.getId());
                    order.setDish_id(dish.getDish_id());
                    new OrderService().create(order);
                    order = new Order();
                }
            }
        } else {
            System.out.println("Pleas, take a sit");
        }
        Constants.LOG.info("END makeOrder");

    }

    public Double calculateBill(Client client) throws SQLException {
        if (!clientIsNull(client)) {
            Order order = new Order();
            OrderService orderDAO = new OrderService();
            Constants.LOG.info("calculate bill");

            System.out.println("The amount for payment is =" + orderDAO.getBillByClientID(client.getId()));
            return orderDAO.getBillByClientID(client.getId());
        } else {
            System.out.println("Pleas,take a sit, and make a order");

        }
        return 0.0;
    }


    public void payForBill(Client client) throws SQLException, IOException {
        if (!clientIsNull(client)) {

            ClientService clientDAO = new ClientService();
            OrderService orderDAO = new OrderService();
            Double bill = calculateBill(client);
            System.out.println("input money");
            selected = br.readLine();
            if (bill.equals(Double.parseDouble(selected))) {
                orderDAO.deleteOrderByClientID(client.getId());
                clientDAO.delete(client);

            } else if (bill.equals(0)) {
                System.out.println("You already payed!");
            } else if (!bill.equals(Double.parseDouble(selected))) {
                System.out.println("you insert wrong amount of money, try again");
                payForBill(client);
            }
        } else {
            System.out.println("Pleas, take sit, and make a order");
        }
    }

    private Client goAway(Client client) throws SQLException, IOException {
        ClientService clientDAO = new ClientService();
        if (clientIsNull(client)) {
            System.out.println("Good Bye");
        } else if (calculateBill(client).equals(0.0)) {
            clientDAO.getClientById(client.getId());
            System.out.println("Good Bye!!!");
            client = null;
        } else {
            System.out.println("You should to pay");
            payForBill(client);
        }
        return client;

    }

}
