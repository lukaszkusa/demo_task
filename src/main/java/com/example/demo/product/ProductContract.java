package com.example.demo.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;

@RequestMapping("/products")
public interface ProductContract {

    @GetMapping(produces = {APPLICATION_JSON_VALUE, APPLICATION_PROBLEM_JSON_VALUE})
    Page<ProductView> getAll(Pageable pageable);

    @GetMapping(
            path = "/{productId}",
            produces = {APPLICATION_JSON_VALUE, APPLICATION_PROBLEM_JSON_VALUE})
    ProductView getOne(@PathVariable("productId") final UUID productId);

    @PostMapping(
            produces = {APPLICATION_JSON_VALUE, APPLICATION_PROBLEM_JSON_VALUE},
            consumes = APPLICATION_JSON_VALUE
    )
    ProductView create(@RequestBody ProductCreateOrder productCreateOrder);

    @DeleteMapping(path = "/{productId}")
    void delete(@PathVariable("productId") final UUID productId);


}
