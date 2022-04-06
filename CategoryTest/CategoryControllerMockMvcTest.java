package ru.gb.gb_shop_mart.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.gb.gb_shop_mart.entity.Category;
import ru.gb.gb_shop_mart.service.CategoryService;
import ru.gb.gb_shop_mart.web.dto.CategoryDto;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
class CategoryControllerMockMvcTest {

    @MockBean
    CategoryService categoryService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void findAllTest() throws Exception {
        List<CategoryDto> categories = new ArrayList<>();

        categories.add(CategoryDto.builder()
                .id(1L)
                .title("Drinks")
                .build());
        categories.add(CategoryDto.builder()
                .id(2L)
                .title("Fruits")
                .build());

        given(categoryService.findAll()).willReturn(categories);

        mockMvc.perform(get("/api/v1/category"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("id")))
                .andExpect(jsonPath("$.[0].id").value("1"))
                .andExpect(jsonPath("$.[0].title").value("Drinks"))
                .andExpect(jsonPath("$.[1].id").value("2"))
                .andExpect(jsonPath("$.[1].title").value("Fruits"));
    }

    @Test
    void testSaveCategoryTest() throws Exception {
        given(categoryService.save(any()))
                .willReturn(new CategoryDto(3L, "Vegetables"));

        mockMvc.perform(post("/api/v1/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(Category.builder()
                                        .title("Vegetables")
                                        .build())))
                .andExpect(status().isCreated());
    }
}