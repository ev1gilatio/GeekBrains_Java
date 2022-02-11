package ru.gb.template;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.gb.ProductDao;
import ru.gb.entity.Product;

@Component
@RequiredArgsConstructor
public class TemplateDAO implements ProductDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Iterable<Product> findAll() {
        return null;
    }

    @Override
    public Product findById(long id) {
        return null;
    }

    @Override
    public String findTitleById(long id) {
        return jdbcTemplate.queryForObject(
                "select title from product where id = ?",
                String.class, id
        );
    }
}
