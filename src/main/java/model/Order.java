package model;

/**
 * Created by Mykola on 07.09.2017.
 */
public class Order extends AbstractModel {
    private Integer id;
    private Integer client_id;
    private Integer dish_id;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client_id=" + client_id +
                ", dish_id=" + dish_id +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public Integer getDish_id() {
        return dish_id;
    }

    public void setDish_id(Integer dish_id) {
        this.dish_id = dish_id;
    }
}
