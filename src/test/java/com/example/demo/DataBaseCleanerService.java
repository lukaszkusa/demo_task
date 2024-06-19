package com.example.demo;

import com.example.demo.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DataBaseCleanerService {

    private ProductRepository productRepository;

    @Transactional
    void clean(){
        productRepository.deleteAllInBatch();
    }
}
