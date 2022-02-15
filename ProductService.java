package ru.gb.springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springboot.model.Product;
import ru.gb.springboot.repository.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findByID(Long id) {
        return repository.findByID(id);
    }

    public void save(Product product) {
        repository.save(product);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
