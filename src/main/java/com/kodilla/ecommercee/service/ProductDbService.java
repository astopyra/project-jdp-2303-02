package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductDbService {

    private final ProductRepository repository;

    public List<Product> getAllProducts(){
        return repository.findAll();
    }

    public Product getProduct(final Long productId) throws ProductNotFoundException{
        return repository.findById(productId).orElseThrow(()->new ProductNotFoundException(
                "Product with given ID not found"));
    }

    public Product saveProduct(final Product product){
        return repository.save(product);
    }

    public void deleteProduct(Long productId) throws ProductNotFoundException {

        Optional<Product> productToDelete = repository.findById(productId);

        if(productToDelete.isPresent()) {
            repository.deleteById(productId);
        } else throw new ProductNotFoundException("Product with given ID not found");
    }
}
