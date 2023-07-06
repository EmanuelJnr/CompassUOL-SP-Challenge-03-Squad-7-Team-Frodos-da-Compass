package br.com.compassuol.pb.challenge.products.service.impl;

import br.com.compassuol.pb.challenge.products.entity.Category;
import br.com.compassuol.pb.challenge.products.entity.Product;
import br.com.compassuol.pb.challenge.products.exception.ResourceNotFoundException;
import br.com.compassuol.pb.challenge.products.payload.CategoryDTO;
import br.com.compassuol.pb.challenge.products.payload.ProductDTO;
import br.com.compassuol.pb.challenge.products.repository.ProductRepository;
import br.com.compassuol.pb.challenge.products.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryServiceImpl categoryServiceImpl;

    public ProductServiceImpl(ProductRepository productRepository, CategoryServiceImpl categoryServiceImpl){
        this.productRepository = productRepository;
        this.categoryServiceImpl = categoryServiceImpl;
    }
    @Override
    public Product createProduct(ProductDTO productDTO) {

        //Product product = mapToEntity(productDTO);

        //Product newProduct = productRepository.save(product);
        //productRepository.save(mapToEntity(productDTO));

        //ProductDTO productResponse = mapToDTO(newProduct);

        return productRepository.save(mapToEntity(productDTO));
    }

    @Override
    public List<ProductDTO> getAllProducts(int page, int linesPerPage, String direction, String orderBy) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(orderBy).descending() : Sort.by(orderBy).ascending();

        Pageable pageable = PageRequest.of(page, linesPerPage, sort);

        Page<Product> products = productRepository.findAll(pageable);

        List<Product> listOfProduct = products.getContent();
        return listOfProduct.stream().map(product -> mapToDTO(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return mapToDTO(product);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        product.setImgUrl(productDTO.getImgUrl());
        product.setDate(productDTO.getDate());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());

        Product updatedProduct = productRepository.save(product);
        return mapToDTO(updatedProduct);
    }

    @Override
    public void deleteProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        productRepository.delete(product);
    }

    private ProductDTO mapToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setDate(product.getDate());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setImgUrl(product.getImgUrl());

        for(Category cdto : product.getCategories()){
            CategoryDTO categoryDTO = categoryServiceImpl.getCategoryById(cdto.getId());
            Category category = new Category();
            category.setName(categoryDTO.getName());
            category.setId(categoryDTO.getId());
            productDTO.getCategories().add(category);
        }

        return productDTO;
    }

    private Product mapToEntity(ProductDTO productDTO){
        Product product = new Product();
        product.setDate(productDTO.getDate());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());

        for(Category cdto : productDTO.getCategories()){
            CategoryDTO categoryDTO = categoryServiceImpl.getCategoryById(cdto.getId());
            Category category = new Category();
            category.setName(categoryDTO.getName());
            category.setId(categoryDTO.getId());
            product.getCategories().add(category);
        }

        return product;
    }
}