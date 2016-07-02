package de.fhws.spring.repository;

import org.springframework.data.repository.CrudRepository;

import de.fhws.spring.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
