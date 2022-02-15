package ru.gb.springboot.controller;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.Message;
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

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String getProductByID(Model model, @PathVariable Long id) {
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

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String showSimpleForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        return "create-product";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String processForm(Product product) {
        service.save(product);

        return "redirect:/all";
    }

    //Не работает
    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    public String editByID(Model model, @RequestParam Long id) {
        Product product = service.findByID(id);
        model.addAttribute("product", product);

        return "create-message";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public String deleteByID(@RequestParam Long id) {
        service.deleteById(id);

        return "redirect:/all";
    }
}
