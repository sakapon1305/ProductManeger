package com.example.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.MusicProduct;
import com.example.repository.MusicProductRepository;

@Service
public class ProductService {

    @Autowired
    private MusicProductRepository musicproductRepository;

    public List<MusicProduct> findAllProducts() {
	return musicproductRepository.findAll();
    }

    public void createProduct(MusicProduct product) {
	product.setTourokushaId("10000000");
	product.setTourokushaDate(LocalDateTime.now());
	musicproductRepository.save(product);
    }

    public MusicProduct findById(Integer id) {
	return musicproductRepository.findById(id).orElse(null);
    }

//Update
    public void updateProduct(Integer id, String musicProductName, int musicProductCount) {
	MusicProduct product = musicproductRepository.findById(id).orElse(null);
	if (product != null) {
	    product.setProductName(musicProductName);
	    product.setProductCount(musicProductCount);
	    product.setKoushinId("10000000");
	    product.setKoushinDate(LocalDateTime.now());
	    musicproductRepository.save(product);
	}
    }
}
