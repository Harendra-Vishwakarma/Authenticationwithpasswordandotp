package com.usernamewithpasswordandotp.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "restaurants")
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long restaurantId;

	private String restaurantName;
	private String address;
	private String phoneNumber;
	private double rating;
	private int deliveryTime;
	private boolean isOpen;

	@OneToMany(mappedBy = "restaurant")
	private List<Product> products;
}
