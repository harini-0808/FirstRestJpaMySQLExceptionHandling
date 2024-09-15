package com.handson.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
	@Id
	//identity strategy is most suitable for mysql db and it differs for other dbs!
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productID;
	private String description;
	private double price;
}
