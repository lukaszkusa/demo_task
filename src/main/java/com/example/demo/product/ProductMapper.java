package com.example.demo.product;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

   ProductView toView(final Product product){
      return ProductView.builder()
               .name(product.getName())
               .price(product.getPrice())
               .build();
   }
}
