package com.example.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class MusicProductTest {

//コンストラクタの挙動確認
    @Test
    void testConstructor() {
	String name = "テスト 太郎";
	MusicProduct product = new MusicProduct(name);
	assertEquals(name, product.getProductName());
    }

// getterとsetterの挙動確認
    @Test
    void testGetter() {
	// テスト用のデータ
	Long id = 1000L;
	String name = "テスト 太郎";
	int productCount = 3;
	String tourokushaId = "10000000";
	LocalDateTime tourokushaDate = LocalDateTime.now();
	String koushinId = "20000000";
	LocalDateTime koushinDate = LocalDateTime.now();

	// MusicProductインスタンスの作成とセット
	MusicProduct product = new MusicProduct(name);
	product.setId(id);
	product.setProductCount(productCount);
	product.setTourokushaId(tourokushaId);
	product.setTourokushaDate(tourokushaDate);
	product.setKoushinId(koushinId);
	product.setKoushinDate(koushinDate);

	// ゲッターが正しい値を返すか確認
	assertEquals(id, product.getId());
	assertEquals(name, product.getProductName()); // 重複しているので修正
	assertEquals(productCount, product.getProductCount());
	assertEquals(tourokushaId, product.getTourokushaId());
	assertEquals(tourokushaDate, product.getTourokushaDate());
	assertEquals(koushinId, product.getKoushinId());
	assertEquals(koushinDate, product.getKoushinDate());
    }

}
