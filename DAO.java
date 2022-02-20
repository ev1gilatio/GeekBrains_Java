package ru.gb.springboot.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springboot.model.Product;
import ru.gb.springboot.utils.Status;

import java.util.List;

public interface DAO extends JpaRepository<Product, Long> {
    List<Product> findAllByStatus(Status status);
    List<Product> findAllByStatus(Status status, Sort sort);
}
