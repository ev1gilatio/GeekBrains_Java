package ru.gb;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> list;

    public ProductRepository() {
        list = new ArrayList<>();

        list.add(new Product(0, "PC", 99500));
        list.add(new Product(1, "Laptop", 49000));
        list.add(new Product(2, "Screen", 15500));
        list.add(new Product(3, "Keyboard", 4500));
        list.add(new Product(4, "Mouse", 4999));
    }

    public Product getProductByID(int id) {
        return list.get(id);
    }

    public void showProductList() {
        System.out.println("ID | TITLE | PRICE");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getId() + " | " +
                    list.get(i).getTitle() + " | " +
                    list.get(i).getPrice());
        }
    }
}
