package com.desafioudstecnologia.controllers;

import com.desafioudstecnologia.dtos.ProductDTO;
import com.desafioudstecnologia.dtos.ProductForm;
import com.desafioudstecnologia.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductForm productForm, UriComponentsBuilder uriComponentsBuilder)
            throws Exception {
        ProductDTO productDTO = productService.createProduct(productForm);
        URI uri = uriComponentsBuilder.path("/products/{id}").buildAndExpand(productDTO.id()).toUri();
        return ResponseEntity.created(uri).body(productDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> productDTOList = this.productService.getAllProducts();
        return ResponseEntity.ok().body(productDTOList);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> getProductByParams(@RequestParam(required = false) String code,
                                                               @RequestParam(required = false) String name,
                                                               @RequestParam(required = false) BigDecimal unitaryPrice) {
        return ResponseEntity.ok().body(this.productService.getAllProductsByParams(code, name, unitaryPrice));
    }

    @PutMapping
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductForm productForm) throws Exception {
        return ResponseEntity.ok().body(this.productService.updateProduct(productForm));
    }

    @DeleteMapping("{code}")
    public ResponseEntity<?> deleteProduct(@PathVariable String code) throws Exception {
        this.productService.deleteProduct(code);
        return ResponseEntity.notFound().build();
    }

}
