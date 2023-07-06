package br.com.compassuol.pb.challenge.products.service;

import br.com.compassuol.pb.challenge.products.entity.Product;
import br.com.compassuol.pb.challenge.products.payload.ProductDTO;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductDTO productDTO);

    List<ProductDTO> getAllProducts(int page, int linesPerPage, String direction, String orderBy);

    ProductDTO getProductById(Long id);

    ProductDTO updateProduct(ProductDTO productDTO, Long id);

    void deleteProductById(Long id);
}