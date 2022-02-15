package ru.gb.springboot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.springboot.dao.JpaDAO;
import ru.gb.springboot.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductRepository {
    private final JpaDAO dao;

    public List<Product> findAll() {
        return (List<Product>) dao.findAll();
    }

    public Product findByID(Long id) {
        return dao.findById(id);
    }

    public void save(Product product) {
        dao.save(product);
    }

    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}
