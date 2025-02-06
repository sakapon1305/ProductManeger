package com.example.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.LoggerFactory;

import com.example.entity.MusicProduct;
import com.example.repository.MusicProductRepository;

import ch.qos.logback.classic.Logger;

class ProductServiceTest {
    @InjectMocks
    private ProductService productService;

    @Mock
    private MusicProductRepository musicProductRepository;
    private MusicProduct musicProduct;

    @BeforeEach
    void setUp() {
	MockitoAnnotations.openMocks(this);
	musicProduct = new MusicProduct("Test Product");
	musicProduct.setId(1L);
	musicProduct.setProductCount(5);
    }

//ログを出力する変数定義
    private static final Logger log = (Logger) LoggerFactory.getLogger(ProductServiceTest.class);

@Test
//01検索メソッド確認テスト
void testFindAllProducts() {
	// モックのリポジトリの設定、Whenはモックオブジェクトのメソッド呼び出し結果を指定するため
	when(musicProductRepository.findAll()).thenReturn(Arrays.asList(musicProduct));
	List<MusicProduct> productList = productService.findAllProducts();
	log.info("Hello World!");
	log.debug(productList.toString());
	//43行目のメソッド呼び出し結果を確認する
	assertNotNull(productList);
	assertEquals(1, productList.size());
	assertEquals(musicProduct, productList.get(0));
	//少なくとも一回は実行されているか確認する
	verify(musicProductRepository, times(1)).findAll();
}

@Test
//02検索メソッド確認
void testFindById() {
	// モックのリポジトリの設定、Whenはモックオブジェクトのメソッド呼び出し結果を指定するため
	when(musicProductRepository.findById(1)).thenReturn(Optional.of(musicProduct));
	MusicProduct product = productService.findById(1);
	log.debug(product.getProductName());
	//43行目のメソッド呼び出し結果を確認する
	assertNotNull(product);
	assertEquals(musicProduct.getProductName(), product.getProductName());
	assertEquals(musicProduct.getId(), product.getId());
	assertEquals(musicProduct.getProductCount(), product.getProductCount());
	//少なくとも一回は実行されているか確認する
	verify(musicProductRepository, times(1)).findById(1);
}

//03updateProductメソッドのテスト
@Test
void testUpdateProduct() {
// モックのリポジトリの設定
when(musicProductRepository.findById(1)).thenReturn(Optional.of(musicProduct));

// updateProductメソッドの実行
productService.updateProduct(1, "Updated Product", 10);

// productの名前と個数が更新されていることを確認
assertEquals("Updated Product", musicProduct.getProductName());
assertEquals(10, musicProduct.getProductCount());
assertNotNull(musicProduct.getKoushinId());
assertNotNull(musicProduct.getKoushinDate());
assertEquals("10000000", musicProduct.getKoushinId());

// リポジトリのsaveメソッドが1回呼ばれることを確認
verify(musicProductRepository, times(1)).save(musicProduct);
}

//04createProductメソッドのテスト
    @Test
    void testCreateProduct() {
//モックのリポジトリの設定
//createProductの実行
	productService.createProduct(musicProduct);
//productに値が格納されていることを確認
	assertNotNull(musicProduct.getTourokushaId());
	assertNotNull(musicProduct.getTourokushaDate());
	assertEquals("10000000", musicProduct.getTourokushaId());

//リポジトリのsaveメソッドが1回呼ばれることを確認
	verify(musicProductRepository, times(1)).save(musicProduct);
    }

//05 updateProductがNotFound時のテスト
@Test
void testNotUpdateProduct() {
// モックのリポジトリの設定、空を返却
when(musicProductRepository.findById(999)).thenReturn(Optional.empty());
// updateProductメソッドの実行
productService.updateProduct(999, "NotFoundProduct", 10);
// リポジトリのsaveメソッドが呼ばれないことを確認
verify(musicProductRepository, times(0)).save(any());
}

}
