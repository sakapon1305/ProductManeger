package com.example;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.controller.ProductController;

@SpringBootTest
class ProductManagerApplicationTests {

    @Autowired
    private ProductController productController;

    @Test
//コントローラーがNull出ない事を確認してアプリケーションが実行されていることを確認する
    void contextLoads() {
	assertThat(productController).isNotNull();
    }

}
