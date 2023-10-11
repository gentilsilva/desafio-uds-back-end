package com.desafioudstecnologia.services;

import com.desafioudstecnologia.domain.product.Product;
import com.desafioudstecnologia.dtos.ProductDTO;
import com.desafioudstecnologia.dtos.ProductForm;
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
    public ProductDTO getProductByCode(String code) throws Exception {
        Optional<Product> product = this.productRepository.findProductByCode(code);
        if(product.isEmpty()) {
            throw new Exception("Produto não encontrado");
        }
        return new ProductDTO(product.get());
    }

    @Transactional(readOnly = true)
    public ProductDTO getProductByName(String name) throws Exception {
        Optional<Product> product = this.productRepository.findProductByName(name);
        if(product.isEmpty()) {
            throw new Exception("Produto não encontrado");
        }
        return new ProductDTO(product.get());
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getProductByPrice(BigDecimal unitaryPrice) throws Exception {
        Optional<List<Product>> products = this.productRepository.findAllProductByUnitaryPrice(unitaryPrice);
        if(products.isEmpty()) {
            throw new Exception("Produtos não encontrado");
        }
        return products.get().stream().map(ProductDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts() {
        return this.productRepository.findAll().stream().map(ProductDTO::new).toList();
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
}
