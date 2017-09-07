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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dishes dishes = (Dishes) o;

        if (!dish_id.equals(dishes.dish_id)) return false;
        if (!dish_name.equals(dishes.dish_name)) return false;
        if (!dish_amount.equals(dishes.dish_amount)) return false;
        return dish_price.equals(dishes.dish_price);
    }

    @Override
    public int hashCode() {
        int result = dish_id.hashCode();
        result = 31 * result + dish_name.hashCode();
        result = 31 * result + dish_amount.hashCode();
        result = 31 * result + dish_price.hashCode();
        return result;
    }
}
