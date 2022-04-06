package ru.gb.gb_shop_mart.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import ru.gb.gb_shop_mart.dao.CategoryDao;
import ru.gb.gb_shop_mart.entity.Category;
import ru.gb.gb_shop_mart.web.dto.CategoryDto;
import ru.gb.gb_shop_mart.web.dto.mapper.CategoryMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CategoryServiceMockitoTest {

    @Mock
    CategoryDao categoryDao;

    @Mock
    CategoryMapper categoryMapper;

    @InjectMocks
    CategoryService categoryService;

    @Test
    void findAllCategoriesTest() {
        // given
        List<Category> categories = new ArrayList<>();
        categories.add(Category.builder()
                .id(1L)
                .title("Drinks")
                .createdBy("user1")
                .createdDate(LocalDateTime.now())
                .lastModifiedBy("user1")
                .lastModifiedDate(LocalDateTime.now())
                .version(0)
                .build());
        categories.add(Category.builder()
                .id(2L)
                .title("Fruits")
                .createdBy("user1")
                .createdDate(LocalDateTime.now())
                .lastModifiedBy("user1")
                .lastModifiedDate(LocalDateTime.now())
                .version(0)
                .build());

        given(categoryDao.findAll()).willReturn(categories);

        // when
        List<CategoryDto> categoryDtos = categoryService.findAll();

        // then
        then(categoryDao).should().findAll();

        assertEquals(categories.size(), categoryDtos.size());
    }

    @Test
    void saveCategoryTest() {
        // given
        Category categoryFromDao = Category.builder()
                .id(1L)
                .title("Drinks")
                .createdBy("user1")
                .createdDate(LocalDateTime.now())
                .lastModifiedBy("user1")
                .lastModifiedDate(LocalDateTime.now())
                .version(0)
                .build();
        CategoryDto drinks = new CategoryDto(null, "Drinks");

        given(categoryDao.save(any(Category.class))).willReturn(categoryFromDao);
        given(categoryMapper.toCategory(any())).will(new ToCategory());
        given(categoryMapper.toCategoryDto(any())).will(new ToCategoryDto());

        // when
        CategoryDto returnedCategoryDto = categoryService.save(drinks);

        // then
        then(categoryDao).should().save(any(Category.class));

        assertEquals(1L, returnedCategoryDto.getId());
    }
}

class ToCategory implements Answer<Category> {

    @Override
    public Category answer(InvocationOnMock invocation) throws Throwable {
        CategoryDto categoryDto = (CategoryDto) invocation.getArgument(0);

        if (categoryDto == null) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.id(categoryDto.getId());
        category.title(categoryDto.getTitle());

        return category.build();
    }
}

class ToCategoryDto implements Answer<CategoryDto> {

    @Override
    public CategoryDto answer(InvocationOnMock invocation) throws Throwable {
        Category category = (Category) invocation.getArgument(0);

        if (category == null) {
            return null;
        }

        CategoryDto.CategoryDtoBuilder categoryDto = CategoryDto.builder();

        categoryDto.id(category.getId());
        categoryDto.title(category.getTitle());

        return categoryDto.build();
    }
}