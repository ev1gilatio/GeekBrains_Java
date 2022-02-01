package ru.gb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.entity.Product;
import ru.gb.repository.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findByID(Integer id) {
        return repository
                .findByID(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public Product edit(Product product) {
        return repository.edit(product);
    }
}
