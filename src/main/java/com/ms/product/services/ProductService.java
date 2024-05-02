package com.ms.product.services;

import com.ms.product.controllers.ProductController;
import com.ms.product.dtos.ProductRecordDto;
import com.ms.product.models.Product;
import com.ms.product.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final Product product;

    public ResponseEntity<Product> saveProduct(ProductRecordDto productRecordDto) {
        BeanUtils.copyProperties(productRecordDto, product);
        productRepository.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> productsList = productRepository.findAll();

        if(!productsList.isEmpty()) {
            for (Product product : productsList) {
                UUID id = product.getIdProduct();
                product.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel()); //<<Hateoas
            }
        }
        return new ResponseEntity<>(productsList, HttpStatus.OK);
    }

    public ResponseEntity<Object> getOneProduct(UUID id) {
        var product = productRepository.findById(id);

        if(product.isEmpty()) {return new ResponseEntity<>("Product not found!", HttpStatus.NOT_FOUND);}

        product.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withRel("Products List"));

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    public ResponseEntity<Object> updateProduct(UUID id, ProductRecordDto productRecordDto) {
        var product = productRepository.findById(id);

        if (product.isEmpty()) {return new ResponseEntity<>("Unable to update product!", HttpStatus.NOT_FOUND);}

        var productModel = product.get();
        BeanUtils.copyProperties(productRecordDto, productModel);
        productRepository.save(productModel);
        return new ResponseEntity<>(productModel, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteProduct(UUID id) {
        var product = productRepository.findById(id);
        if (product.isPresent()){
            productRepository.delete(product.get());
            return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Unable to delete product", HttpStatus.NOT_FOUND);
    }
}
