package ru.gb.gb_shop_mart.web.dto.mapper;

import org.mapstruct.Mapper;
import ru.gb.gb_shop_mart.entity.Category;
import ru.gb.gb_shop_mart.web.dto.CategoryDto;

@Mapper
public interface CategoryMapper {
    Category toCategory(CategoryDto categoryDto);

    CategoryDto toCategoryDto(Category category);
}
