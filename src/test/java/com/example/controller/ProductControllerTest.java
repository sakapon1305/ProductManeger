package com.example.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.example.entity.MusicProduct;
import com.example.service.ProductService;

class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private Model model;

    @BeforeEach
//Modelを入れておく
    void setUp() {
	MockitoAnnotations.openMocks(this);
	model = new RedirectAttributesModelMap();
    }

//商在庫一覧表示のテスト
    @Test
    void testListProducts() {
	String result = productController.listProducts(model);
	assertEquals("common/index", result);
	assertNotNull(model.asMap().get("productList"));
	// productDeleteServiceが一度も呼ばれていないことを確認するメソッド
	verify(productService, times(1)).findAllProducts();
    }

//商品作成画面遷移テスト
    @Test
    void testShowCreateProductForm() {
	String result = productController.showCreateProductForm(model);
	assertEquals("layouts/create", result);
	assertNotNull(model.asMap().get("product"));
    }

//商品作成画面保存ボタンテスト
    @Test
    void testCreateProduct() {
	String productName = "テスト　太郎";
	String result = productController.createProduct(productName);
	assertEquals("redirect:/common", result);
	verify(productService, times(1)).createProduct(any(MusicProduct.class));
    }

//編集画面遷移テスト
    @Test
    void testShowEditForm() {
	int productId = 1;
	MusicProduct testProduct = new MusicProduct("テスト　太郎");
	// メソッド実行時指定
	when(productService.findById(productId)).thenReturn(testProduct);
	String result = productController.showEditForm(productId, model);
	assertEquals("layouts/edit", result);
	assertNotNull(model.asMap().get("product"));
	verify(productService, times(1)).findById(productId);
    }

//商品作成画面保存ボタンテスト
    @Test
    void testUpdateProduct() {
	String testProductName = "テスト　太郎";
	Integer testid = 1001;
	int testCount = 4;
	String result = productController.updateProduct(testid, testProductName, testCount);
	assertEquals("redirect:/common", result);
	verify(productService, times(1)).updateProduct(testid, testProductName, testCount);
    }

}
