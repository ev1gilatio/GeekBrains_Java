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

    public Product findByID(Integer id) {
        return repository
                .findByID(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
