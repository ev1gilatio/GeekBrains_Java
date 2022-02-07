package ru.gb.springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.springboot.model.Product;
import ru.gb.springboot.service.ProductService;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @ResponseBody
    @GetMapping
    public String defaultRequest() {
        return "empty";
    }

    @GetMapping("/{id}")
    public String getProductByID(Model model, @PathVariable Integer id) {
        Product product;
        product = service.findByID(id);

        model.addAttribute("product", product);

        return "product";
    }

    @GetMapping("/all")
    public String getAllProducts(Model model) {
        model.addAttribute("list", service.findAll());

        return "product-list";
    }
}
