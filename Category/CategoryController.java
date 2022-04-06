package ru.gb.gb_shop_mart.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.gb_shop_mart.service.CategoryService;
import ru.gb.gb_shop_mart.web.dto.CategoryDto;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getCategoryList() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends CategoryDto> getCategory(@PathVariable("id") Long id) {
        if (id != null) {
            CategoryDto dto = categoryService.findById(id);

            if (dto != null) {
                return new ResponseEntity<>(dto, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> handlePost(@Validated @RequestBody CategoryDto dto) {
        CategoryDto savedCategory = categoryService.save(dto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create("/api/v1/category/" + savedCategory.getId()));

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> handleUpdate(@PathVariable("id") Long id,
                                          @Validated @RequestBody CategoryDto dto) {
        dto.setId(id);
        categoryService.save(dto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        categoryService.deleteById(id);
    }
}
