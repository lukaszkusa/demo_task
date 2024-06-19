package com.example.demo.product;

import com.example.demo.AbstractIntegrationTest;
import io.restassured.http.ContentType;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


class ProductControllerIntegrationTest extends AbstractIntegrationTest {
    @Autowired ProductService productService;
    private static final String PRODUCTS_PATH = "/products";

    @Test
    void getAll_emptyResponse(){

        final var response =
                given()
                        .accept(ContentType.JSON)
                        .when()
                        .get( super.baseUrl() + PRODUCTS_PATH)
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .jsonPath()
                        .getList("content", ProductView.class);

        assertThat(response).isEmpty();
    }

    @Test
    void getAll(){

        productService.create(ProductFactory.buildProduct("produkt1", 101));
        productService.create(ProductFactory.buildProduct("produkt2", 102));
        productService.create(ProductFactory.buildProduct("produkt3", 103));

        final var response =
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get(super.baseUrl() + PRODUCTS_PATH)
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .jsonPath()
                        .getList("content", ProductView.class);

        assertThat(response).isNotNull().hasSize(3);
    }

    @Test
    void getOne(){
        final var product =
                productService.create(ProductFactory.buildProduct("produkt1", 101));

        String path =  PRODUCTS_PATH + "/" + product.getId().toString();

        final var responseProduct =
                given().contentType(ContentType.JSON)
                        .when()
                        .get(super.baseUrl() + path)
                        .then()
                        .extract()
                        .as(ProductView.class);

        assertThat(responseProduct.name).hasToString("produkt1");
        assertThat(responseProduct.price).isEqualTo(101);
    }

    @Test
    void getOne_notFound(){

        String path =  PRODUCTS_PATH + "/" + UUID.randomUUID();
        given().contentType(ContentType.ANY)
                .when()
                .get(super.baseUrl() + path)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    void create(){
        final var product =
                ProductFactory.buildProductCreateOrder("produkt1", 101);

        final var responseProduct =
                given().contentType(ContentType.JSON)
                        .body(product)
                        .when()
                        .post(super.baseUrl() + PRODUCTS_PATH)
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .as(ProductView.class);

        assertThat(responseProduct.name).hasToString("produkt1");
        assertThat(responseProduct.price).isEqualTo(101);
    }

    @Test
    void delete(){

        final var product =
                productService.create(ProductFactory.buildProduct("produkt1", 101));

        String path =  PRODUCTS_PATH + "/" + product.getId().toString();

        given().contentType(ContentType.JSON)
                .when()
                .delete(super.baseUrl() + path)
                .then()
                .statusCode(HttpStatus.SC_OK);

        given().contentType(ContentType.ANY)
                .when()
                .get(super.baseUrl() + path)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);

    }

    @Test
    void delete_notFound(){

        String path =  PRODUCTS_PATH + "/" + UUID.randomUUID();

        given().contentType(ContentType.JSON)
                .when()
                .delete(super.baseUrl() + path)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

}
