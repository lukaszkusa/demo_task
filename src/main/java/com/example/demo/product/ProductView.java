package com.example.demo.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;


@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductView {

    @JsonView
    String name;
    @JsonView
    Double price;
}
