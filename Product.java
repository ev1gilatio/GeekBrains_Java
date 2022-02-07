package ru.gb.springboot.model;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer id;
    private String name;
    private Integer price;
}
