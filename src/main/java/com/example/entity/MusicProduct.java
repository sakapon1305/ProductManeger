package com.example.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRODUCT_KIHON_JOHO")
@Getter
@Setter
public class MusicProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_SEQNO")
    private Long id;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "PRODUCT_COUNT")
    private int productCount;

    @Column(name = "TOUROKUSHA_ID")
    private String tourokushaId;

    @Column(name = "TOUROKUSHA_DATE")
    private LocalDateTime tourokushaDate;

    @Column(name = "KOUSHIN_ID")
    private String koushinId;

    @Column(name = "KOUSHIN_DATE")
    private LocalDateTime koushinDate;

    public MusicProduct(String productName) {
	this.productName = productName;
	this.productCount = 0;
    }

    public MusicProduct() {
	// TODO 自動生成されたコンストラクター・スタブ
    }
}