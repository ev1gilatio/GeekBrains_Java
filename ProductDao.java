package ru.gb;

import ru.gb.entity.Product;

public interface ProductDao {
    Iterable<Product> findAll();
    Product findById(long id);
    String findTitleById(long id);
}
