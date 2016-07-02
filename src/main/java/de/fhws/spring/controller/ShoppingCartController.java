package de.fhws.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.fhws.spring.model.Product;
import de.fhws.spring.model.ShoppingCart;
import de.fhws.spring.repository.ProductRepository;
import de.fhws.spring.repository.ShoppingCartRepository;

@RestController
@RequestMapping(value = "/shoppingcarts/")
public class ShoppingCartController {

    @Inject
    ShoppingCartRepository shoppingCartRepository;

    // This cannot be used by microservices with UIs
    @Inject
    ProductRepository productRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ShoppingCart getShopingCart(@PathVariable final long id) {
	return shoppingCartRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ShoppingCart save(ShoppingCart cart) {
	return shoppingCartRepository.save(cart);
    }

    @RequestMapping(value = "{id}/items", method = RequestMethod.GET)
    public List<Product> getShoppingCartProducts(@PathVariable final long id) {
	final ShoppingCart cart = shoppingCartRepository.findOne(id);
	final List<Product> productList = new ArrayList<>();
	for (final Long productId : cart.getItemIds()) {
	    final Product product = productRepository.findOne(productId);
	    if (product == null) {
		productList.add(new Product(productId, "PRODUCT NO LONGER AVAILABLE!", 0));
	    } else {
		productList.add(product);
	    }
	}
	return productList;
    }

    @RequestMapping(value = "{id}/items", method = RequestMethod.PUT)
    public ShoppingCart addProductToShoppingCart(@PathVariable final long id, @RequestBody final Product product) {
	final ShoppingCart cart = shoppingCartRepository.findOne(id);
	cart.getItemIds().add(product.getId());
	return shoppingCartRepository.save(cart);
    }

    @RequestMapping(value = "{id}/total", method = RequestMethod.GET)
    public double getShoppingCartTotal(@PathVariable final long id) {
	final ShoppingCart cart = shoppingCartRepository.findOne(id);
	double total = 0;
	for (final Map.Entry<Long, Long> entry : cart.getNumberOfItemsById().entrySet()) {
	    final double price = productRepository.findOne(entry.getKey()).getPrice();
	    total += (price * entry.getValue());
	}
	return total;
    }

    @RequestMapping(value = "{id}/items", method = RequestMethod.DELETE)
    public ShoppingCart deleteItemFromCart(@PathVariable final long id, @RequestBody Product product) {
	final ShoppingCart cart = shoppingCartRepository.findOne(id);
	cart.getItemIds().remove(product.getId());
	return shoppingCartRepository.save(cart);
    }

}
