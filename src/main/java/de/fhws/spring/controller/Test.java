package de.fhws.spring.controller;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import de.fhws.spring.model.Product;

public class Test {

    public static void main(String[] args) {
	final List<Product> list = Arrays.asList(new Product(1, "Test", 40), new Product(1, "Test", 40), new Product(1, "Test", 40));

	System.out.println(list);

	final Map<Long, Long> numberOfProductsById = list.stream().collect(groupingBy(Product::getId, counting()));

	System.out.println("JO " + numberOfProductsById);

    }

}
