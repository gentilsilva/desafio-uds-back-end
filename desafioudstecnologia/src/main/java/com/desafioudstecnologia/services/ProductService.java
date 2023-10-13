package com.desafioudstecnologia.services;

import com.desafioudstecnologia.domain.product.Product;
import com.desafioudstecnologia.dtos.product.ProductDTO;
import com.desafioudstecnologia.dtos.product.ProductForm;
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
    public ProductDTO createProduct(ProductForm productForm) throws Exception {
        Optional<Product> optionalProductCode = this.productRepository.findProductByCode(productForm.code());
        Optional<Product> optionalProductName = this.productRepository.findProductByName(productForm.name());

        if(optionalProductCode.isPresent() || optionalProductName.isPresent()) {
            throw new Exception("Produto já cadastrado");
        }

        Product product = new Product(productForm);
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
    public ProductDTO updateProduct(ProductForm productForm) throws Exception {
        Optional<Product> product = this.productRepository.findProductByCode(productForm.code());
        if(product.isPresent()) {
            product.get().update(productForm);
            return new ProductDTO(product.get());
        }
        throw new Exception("Produto não encontrado");
    }

    @Transactional
    public void deleteProduct(String code) throws Exception {
        Optional<Product> product = this.productRepository.findProductByCode(code);
        if(product.isPresent()) {
            this.productRepository.delete(product.get());
        } else {
            throw new Exception("Produto não encontrado");
        }
    }

    @Transactional(readOnly = true)
    public Product getProductByCode(String code) throws Exception {
        Optional<Product> product = this.productRepository.findProductByCode(code);
        if(product.isPresent()) {
            return product.get();
        }
        throw new Exception("Produto não encontrado.");
    }
}
