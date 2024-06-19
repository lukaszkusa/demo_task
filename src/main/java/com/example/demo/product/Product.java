package com.example.demo.product;

import com.example.demo.AbstractEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
public class Product extends AbstractEntityBase {

    @Serial
    private static final long serialVersionUID = 8870457125093728572L;

    @Column(nullable = false, name = "product_name")
    private String name;

    @Column(nullable = false)
    private Double price;

}
