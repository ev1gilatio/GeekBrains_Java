package ru.gb.springboot.repository;

import org.springframework.stereotype.Component;
import ru.gb.springboot.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductRepository {
    List<Product> list;
    private int count = 5;

    public ProductRepository() {
        list = new ArrayList<>();

        list.add(new Product(0, "PC", 99500));
        list.add(new Product(1, "Laptop", 49000));
        list.add(new Product(2, "Screen", 15500));
        list.add(new Product(3, "Keyboard", 4500));
        list.add(new Product(4, "Mouse", 4999));
    }

    public List<Product> findAll() {
        return list;
    }

    public Optional<Product> findByID(Integer id) {
        if (id < list.size()) {
            return Optional.of(list.get(id));
        } else {
            return Optional.empty();
        }
    }

    public List<Product> getList() {
        return list;
    }
}
