package com.example.demo.product;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Service
@AllArgsConstructor
public class ProductService {

    ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<Product> getAllProducts( final Pageable pageable){
       return this.productRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Product getOne( final UUID productId){
        return this.productRepository
                .findById(productId)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Transactional
    public Product create(final Product product){
        return this.productRepository.save(product);
    }
    @Transactional
    public void delete( final UUID productId){
        this.productRepository.deleteById(productId);
    }
}
