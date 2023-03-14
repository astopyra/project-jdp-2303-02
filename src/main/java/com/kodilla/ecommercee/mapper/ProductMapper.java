package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMapper {

    public Product mapToProduct(ProductDto productDto){
        return new Product(productDto.getId(), productDto.getProductName(), productDto.getProductPrice());
    }

    public ProductDto mapToProductDto(Product product){
        return new ProductDto(product.getId(), product.getProductName(), product.getProductPrice());
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> products){
        return products.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());

    }


}
