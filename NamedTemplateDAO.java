package ru.gb.namedTemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.gb.ProductDao;
import ru.gb.entity.Product;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class NamedTemplateDAO implements ProductDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Iterable<Product> findAll() {
        String sql = "select * from product";

        return namedParameterJdbcTemplate.query(sql, (rs, rowNum) ->
                Product.builder()
                        .id(rs.getLong("id"))
                        .title(rs.getString("title"))
                        .cost(rs.getBigDecimal("cost"))
                        .date(rs.getDate("manufacture_date").toLocalDate())
                        .build());
    }

    @Override
    public Product findById(long id) {
        return null;
    }

    @Override
    public String findTitleById(long id) {
        String sql = "select title from product where id = :productId";
        HashMap<String, Object> namedParams = new HashMap<>();
        namedParams.put("productId", id);

        return namedParameterJdbcTemplate.queryForObject(sql, namedParams, String.class);
    }
}
