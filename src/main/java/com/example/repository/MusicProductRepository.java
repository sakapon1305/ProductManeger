package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.MusicProduct;

@Repository
public interface MusicProductRepository extends JpaRepository<MusicProduct, Integer> {
// 特定のIDの商品を削除するメソッド（JpaRepositoryに標準で提供されている）
    void deleteByIdIn(List<Long> productIds);
}