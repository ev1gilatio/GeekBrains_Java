package ru.gb.springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.springboot.model.Product;
import ru.gb.springboot.service.ProductService;
import ru.gb.springboot.utils.Status;

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
        product = service.findById(id);

        model.addAttribute("product", product);

        return "product";
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public String getAllProducts(Model model) {
        model.addAttribute("list", service.findAllActive());

        return "product-list";
    }

    @RequestMapping(path = "/all/sorted", method = RequestMethod.GET)
    public String getAllProductsSorted(Model model, @RequestParam String sort) {
        if (sort.equals("desc")) {
            model.addAttribute("list",
                    service.findAllActiveSortedByPrice(Sort.Direction.DESC));
        } else if (sort.equals("asc")) {
            model.addAttribute("list",
                    service.findAllActiveSortedByPrice(Sort.Direction.ASC));
        }

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
        product.setStatus(Status.ACTIVE);
        service.save(product);

        return "redirect:/all";
    }

    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    public String editByID(Model model, @RequestParam Long id) {
        Product product = service.findById(id);
        model.addAttribute("product", product);

        return "create-product";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public String deleteByID(@RequestParam Long id) {
        service.disableById(id);

        return "redirect:/all";
    }
}
