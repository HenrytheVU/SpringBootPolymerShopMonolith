package de.fhws.spring.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.fhws.spring.model.Product;
import de.fhws.spring.repository.ProductRepository;

@RestController
@RequestMapping(value = "/products/")
public class ProductController {

    @Inject
    ProductRepository repository;

    @RequestMapping(method = RequestMethod.POST)
    public Product save(@RequestBody final Product product) {
	return repository.save(product);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Product findOne(@PathVariable final long id) {
	return repository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Product> findALl() {
	return repository.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Product updateOne(@PathVariable final long id, @RequestBody final Product product) {
	product.setId(id);
	return repository.save(product);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Iterable<Product> updateAll(@RequestBody final Iterable<Product> products) {
	return repository.save(products);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteOne(@PathVariable final long id) {
	repository.delete(id);
    }

}
