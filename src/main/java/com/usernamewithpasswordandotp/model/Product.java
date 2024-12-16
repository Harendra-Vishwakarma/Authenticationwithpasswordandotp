package com.usernamewithpasswordandotp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	private String productName;
	private String description;
	private double price;
	private boolean isAvailable;
	private String imgUrl;
	@ManyToOne
	@JoinColumn(name = "product_category_id")
	private ProductCategory category;
	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;
}
