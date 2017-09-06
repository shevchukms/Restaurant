package model;


public class Dishes extends AbstractModel {

    private Integer dish_id;
    private String dish_name;
    private Double dish_amount;
    private Double dish_price;

    public Integer getDish_id() {
        return dish_id;
    }

    public void setDish_id(Integer dish_id) {
        this.dish_id = dish_id;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public Double getDish_amount() {
        return dish_amount;
    }

    public void setDish_amount(Double dish_amount) {
        this.dish_amount = dish_amount;
    }

    public Double getDish_price() {
        return dish_price;
    }

    @Override
    public String toString() {
        return "Dishes{" +
                "dish_id=" + dish_id +
                ", dish_name='" + dish_name + '\'' +
                ", dish_amount=" + dish_amount +
                ", dish_price=" + dish_price +
                '}';
    }

    public void setDish_price(Double dish_price) {
        this.dish_price = dish_price;
    }
}
