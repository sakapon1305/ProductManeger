package com.example.service;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.repository.MusicProductRepository;

class ProductDeleteServiceTest {

// テスト対象のサービスクラスを注入する
    @InjectMocks
    private ProductDeleteService productDeleteService;

// モック化されたリポジトリ
    @Mock
    private MusicProductRepository productRepository;

// テストのセットアップ処理
    @BeforeEach
    void setUp() {
	// Mockitoのモック初期化
	MockitoAnnotations.openMocks(this);
    }

//メソッド正常系確認テスト
    @Test
    void testDeleteProductsByIds() {
	// 削除対象の商品IDリストを準備
	List<Long> productIds = Arrays.asList(1L, 2L, 3L);

	// サービスメソッドの実行
	productDeleteService.deleteProductsByIds(productIds);

	// リポジトリの deleteByIdIn メソッドが1回だけ呼ばれ、正しい引数が渡されたことを確認
	verify(productRepository, times(1)).deleteByIdIn(productIds);
    }

//空の場合確認テスト
    @Test
    void testDeleteProductsByIds_EmptyList() {
	// 空のリストを渡す
	List<Long> emptyProductIds = Arrays.asList();

	// サービスメソッドの実行
	productDeleteService.deleteProductsByIds(emptyProductIds);

	// deleteByIdIn は一度も呼び出されないことを確認
	verify(productRepository, times(0)).deleteByIdIn(emptyProductIds);
    }
}
