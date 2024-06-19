package com.example.demo.product;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
@RestController
@AllArgsConstructor
public class ProductController implements ProductContract {

    ProductMapper productMapper;
    ProductService productService;
    @Override
    @Transactional(readOnly = true)
    public Page<ProductView> getAll(Pageable pageable) {
        final var allProducts = this.productService.getAllProducts(pageable);
        return allProducts.map(productMapper::toView);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductView getOne(final UUID productId) {
        return productMapper.toView(this.productService.getOne(productId));
    }

    @Override
    @Transactional
    public ProductView create(final ProductCreateOrder productCreateOrder) {
        final var product =
                new Product(productCreateOrder.getName(), productCreateOrder.getPrice());
        return productMapper.toView(this.productService.create(product));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        final var product = this.productService.getOne(id);
        this.productService.delete(product.getId());
    }
}
