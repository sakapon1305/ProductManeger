package com.example.repository;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class) // JUnit5でMockito使うには必要
class MusicProductRepositoryTest {

    @Mock // モック（スタブ）に置き換えたいインスタンスに定義。すべてのメソッドがモックになる
//@Spy // 一部のメソッドだけモックにしたいときはこれを定義
    private MusicProductRepository mockRepository;

    @BeforeEach // 自クラスのモック初期化。
    void setUp() {
	MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteByIdIn() {
	// テストデータ準備
	List<Long> productIds = Arrays.asList(1L, 2L, 3L);

	// doNothing().when(...)はメソッドが何もしない動作を設定するため
	doNothing().when(mockRepository).deleteByIdIn(productIds);

	// 実行
	mockRepository.deleteByIdIn(productIds);

	// deleteByIdInが正しく呼ばれていること
	verify(mockRepository, times(1)).deleteByIdIn(productIds);
    }
}
