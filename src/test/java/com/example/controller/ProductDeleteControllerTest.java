package com.example.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.example.service.ProductDeleteService;

class ProductDeleteControllerTest {

    @InjectMocks
    private ProductDeleteController productDeleteController;

    @Mock
    private ProductDeleteService productDeleteService;

    private Model model;

    @BeforeEach
    void setUp() {
	MockitoAnnotations.openMocks(this);
	model = new RedirectAttributesModelMap();
    }

//商品が選択されない場合のテスト
    @Test
    void testDeleteProducts_EmptyList() {
	List<Long> selectedProductIds = new ArrayList<Long>();

	String result = productDeleteController.deleteProducts(selectedProductIds, model);
	assertEquals("redirect:/common", result);
	assertEquals("削除する商品を選択してください。", model.asMap().get("error"));
	// productDeleteServiceが一度も呼ばれていないことを確認するメソッド
	verify(productDeleteService, times(0)).deleteProductsByIds(selectedProductIds);
    }

//正常系テスト
    @Test
    void testDeleteProducts_Successfull() {
	List<Long> selectedProductIds = Arrays.asList(1L, 2L, 3L);

	String result = productDeleteController.deleteProducts(selectedProductIds, model);

	assertEquals("redirect:/common", result);
	assertEquals("選択された商品が削除されました。", model.asMap().get("message"));
	verify(productDeleteService, times(1)).deleteProductsByIds(selectedProductIds);
    }

}
