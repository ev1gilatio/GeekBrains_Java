package ru.gb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {
    @Autowired
    private ProductRepository repository;
    private List<Product> list;

    public Cart(ProductRepository repository) {
        this.repository = repository;
        list = new ArrayList<>();
    }

    public void addProductByID(int id) {
        list.add(repository.getProductByID(id));
    }

    public void removeProductByID(int id) {
        list.remove(id);
    }

    public void showContent() {
        System.out.println("ID | TITLE | PRICE");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getId() + " | " +
                    list.get(i).getTitle() + " | " +
                    list.get(i).getPrice());
        }
    }
}
