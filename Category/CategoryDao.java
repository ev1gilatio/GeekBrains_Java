package ru.gb.gb_shop_mart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.gb_shop_mart.entity.Category;

import java.util.Optional;

public interface CategoryDao extends JpaRepository<Category, Long> {
    Optional<Category> findByTitle(String title);
}
