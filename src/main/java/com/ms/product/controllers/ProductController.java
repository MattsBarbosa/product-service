package com.ms.product.controllers;

import com.ms.product.dtos.ProductRecordDto;
import com.ms.product.models.Product;
import com.ms.product.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products") //<<uri
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        return productService.saveProduct(productRecordDto);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")              // \/ PathVariable para obter o ID da URI
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id){
        return productService.getOneProduct(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid ProductRecordDto productRecordDto) {
        return productService.updateProduct(id, productRecordDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(value = "id") UUID id) {
        return productService.deleteProduct(id);
    }
}
