package com.example.demo.product;

import java.io.Serial;

class ProductNotFoundException extends RuntimeException{


    @Serial
    private static final long serialVersionUID = 6908956458502323180L;

    public ProductNotFoundException(){
        super("Product not found");
    }
}
