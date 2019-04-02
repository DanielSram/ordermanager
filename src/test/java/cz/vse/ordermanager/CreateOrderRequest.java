package cz.vse.ordermanager;

import cz.vse.ordermanager.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CreateOrderRequest {

    private Customer customer = new Customer();
    private List<Item> items = new ArrayList<>();

    public void addItem(int quantity, String product) {
        items.add(new Item(product, quantity));
    }

    @Getter
    @AllArgsConstructor
    class Item {
        private String product;
        private int quantity;
    }
}
