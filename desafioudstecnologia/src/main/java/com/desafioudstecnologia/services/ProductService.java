package com.desafioudstecnologia.services;

import com.desafioudstecnologia.domain.product.Product;
import com.desafioudstecnologia.dtos.product.ProductDTO;
import com.desafioudstecnologia.dtos.product.ProductForm;
import com.desafioudstecnologia.exeptions.DuplicateRecordException;
import com.desafioudstecnologia.exeptions.RecordNotFoundException;
import com.desafioudstecnologia.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductDTO createProduct(ProductForm productForm) {
        this.checkForDuplicate(productForm);
        Product product = new Product(productForm);
        this.productRepository.save(product);
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts() {
        return this.productRepository.findAll().stream().map(ProductDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProductsByParams(String code, String name, BigDecimal unitaryPrice) {
        List<Product> productList = this.productRepository.findAllProductByCodeOrNameOrUnitaryPrice(code, name, unitaryPrice);
        return productList.stream().map(ProductDTO::new).toList();
    }

    @Transactional
    public ProductDTO updateProduct(ProductForm productForm) {
        Product product = this.productRepository.findProductByCode(productForm.code()).orElseThrow(() ->
                new RecordNotFoundException(productForm.code()));
        product.update(productForm);
        return new ProductDTO(product);

    }

    @Transactional
    public void deleteProduct(String code) {
        Product product = this.productRepository.findProductByCode(code).orElseThrow(() -> new RecordNotFoundException(code));
        this.productRepository.delete(product);

    }

    @Transactional(readOnly = true)
    public Product getProductByCode(String code) {
        return this.productRepository.findProductByCode(code).orElseThrow(() -> new RecordNotFoundException(code));
    }

    public void checkForDuplicate(ProductForm productForm) {
        Optional<Product> productCode = this.productRepository.findProductByCode(productForm.code());
        Optional<Product> productName = this.productRepository.findProductByName(productForm.name());

        if(productCode.isPresent()) {
            throw new DuplicateRecordException(productForm.code());
        }

        if(productName.isPresent()) {
            throw new DuplicateRecordException(productForm.name());
        }
    }
}
