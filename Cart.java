package ru.gb.thymeleaf.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.thymeleaf.service.ProductService;

import java.util.List;

@Getter
@RequiredArgsConstructor
@Component
public class Cart {
    private final ProductService service;
    private final List<Product> content;

    public void addProduct(Long id) {
        if (content.stream().anyMatch((p) -> p.getId().equals(id))) {
            return;
        }
        content.add(service.findById(id));
    }

    public void removeProduct(Long id) {
        content.stream()
                .filter((p) -> p.getId().equals(id))
                .findAny()
                .ifPresent(content::remove);
    }
}
