package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.repository.MusicProductRepository;

@Service
public class ProductDeleteService {

    @Autowired
    private MusicProductRepository productRepository;

// 商品IDのリストを受け取って、削除するメソッド
// トランザクションを管理するため、@Transactional を追加
    @Transactional
    public void deleteProductsByIds(List<Long> productIds) {
	// 商品IDのリストをもとに、各商品を削除
	if (productIds == null || productIds.isEmpty()) {
	    return; // 空リストの場合は何もしない
	}
	productRepository.deleteByIdIn(productIds);
    }
}