package ru.gb.gb_shop_mart.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.gb.gb_shop_mart.config.ShopConfiguration;
import ru.gb.gb_shop_mart.entity.Category;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(ShopConfiguration.class)
class CategoryDaoTest {
    private static final String CATEGORY_NAME = "Drinks";

    @Autowired
    CategoryDao categoryDao;

    @Test
    public void saveTest() {
        Category category = Category.builder()
                .title(CATEGORY_NAME)
                .build();

        Category savedCategory = categoryDao.save(category);

        assertAll(
                () -> assertEquals(1L, savedCategory.getId()),
                () -> assertEquals(CATEGORY_NAME, savedCategory.getTitle()),
                () -> assertEquals(0, savedCategory.getVersion()),
                () -> assertEquals("User", savedCategory.getCreatedBy()),
                () -> assertNotNull(savedCategory.getCreatedDate()),
                () -> assertEquals("User", savedCategory.getLastModifiedBy()),
                () -> assertNotNull(savedCategory.getLastModifiedDate())
        );
    }
}
