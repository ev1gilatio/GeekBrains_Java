package ru.gb.rest_1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.rest_1.entity.Cart;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final Cart cart;

    @GetMapping
    public String getProductList() {
        if (cart.getContent().isEmpty()) {
            return "Cart is empty";
        }

        return String.valueOf(cart.getCartContent());
    }

    @GetMapping("/remove")
    public void removeProduct(@RequestParam(name = "id") Long id) {
        cart.removeProduct(id);
    }

    @GetMapping("/add")
    public void addProduct(@RequestParam(name = "id") Long id) {
        cart.addProduct(id);
    }
}
