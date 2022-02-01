package ru.gb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.entity.Product;
import ru.gb.service.ProductService;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String showSimpleForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        return "create-product";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String processForm(Product product) {
        if (product.getId() == null) {
            service.save(product);
        } else {
            service.edit(product);
        }

        return "redirect:/product/all";
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String getProductByID(Model model, @PathVariable Integer id) {
        Product product;
        product = service.findByID(id);

        model.addAttribute("product", product);

        return "product";
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public String getAllProducts(Model model) {
        model.addAttribute("list", service.findAll());

        return "product-list";
    }

    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    public String editByID(Model model, @RequestParam Integer id) {
        Product product = service.findByID(id);
        model.addAttribute("product", product);

        return "create-product";
    }
}
