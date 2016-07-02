package de.fhws.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    protected Product() {
	// no-args constructor for JPA specs
    }

    public Product(long id, String name, double price) {
	this.id = id;
	this.name = name;
	this.price = price;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public double getPrice() {
	return price;
    }

    public void setPrice(double price) {
	this.price = price;
    }

    @Override
    public String toString() {
	return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
    }

}
