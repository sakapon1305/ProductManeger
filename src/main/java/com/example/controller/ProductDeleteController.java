package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.service.ProductDeleteService;

@Controller
public class ProductDeleteController {

    @Autowired
    private ProductDeleteService productDeleteService;

// 商品を削除するためのメソッド
    @PostMapping("/deleteProducts")
    public String deleteProducts(@RequestParam("selectedProductIds") List<Long> selectedProductIds, Model model) {
	if (selectedProductIds.isEmpty()) {
	    model.addAttribute("error", "削除する商品を選択してください。");
	    return "redirect:/common"; // 商品一覧ページにリダイレクト
	}
	// 商品削除サービスを呼び出す
	productDeleteService.deleteProductsByIds(selectedProductIds);

	// 削除完了後、一覧ページにリダイレクト
	model.addAttribute("message", "選択された商品が削除されました。");
	return "redirect:/common"; // 商品一覧のページにリダイレクト
    }
}