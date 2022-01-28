package ru.gb;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProductConfig.class);

        ProductRepository repository = context.getBean(ProductRepository.class);
        Cart cart = context.getBean(Cart.class);

        //Вывод списка продуктов
        repository.showProductList();

        System.out.println();

        //Добавление продукта в корзину
        cart.addProductByID(0);
        cart.addProductByID(1);
        cart.addProductByID(4);
        cart.showContent();

        System.out.println();

        //Удаление продукта из корзины
        cart.removeProductByID(1);
        cart.showContent();
    }
}
