package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.MusicProduct;
import com.example.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

// 在庫一覧表示
    @GetMapping("/common")
    public String listProducts(Model model) {
	List<MusicProduct> productList = productService.findAllProducts();
	model.addAttribute("productList", productList);
	return "common/index";
    }

// 商品登録画面への遷移
    @GetMapping("/createForm")
    public String showCreateProductForm(Model model) {
	model.addAttribute("product", new MusicProduct());
	return "layouts/create"; // create.html に遷移
    }

// 商品追加処理
    @PostMapping("/create")
    public String createProduct(@RequestParam String productName) {
	productService.createProduct(new MusicProduct(productName));
	return "redirect:/common";
    }

// 商品編集画面遷移
    @GetMapping("/editForm/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
	MusicProduct product = productService.findById(id);
	model.addAttribute("product", product);
	return "layouts/edit";
    }

// 商品編集処理
    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Integer id, @RequestParam String productName,
	    @RequestParam int productCount) {
	productService.updateProduct(id, productName, productCount);
	return "redirect:/common";
    }
}