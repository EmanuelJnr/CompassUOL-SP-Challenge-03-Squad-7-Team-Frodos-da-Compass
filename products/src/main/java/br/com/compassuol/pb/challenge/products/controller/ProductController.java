package br.com.compassuol.pb.challenge.products.controller;

import br.com.compassuol.pb.challenge.products.entity.Category;
import br.com.compassuol.pb.challenge.products.entity.Product;
import br.com.compassuol.pb.challenge.products.payload.ProductDTO;
import br.com.compassuol.pb.challenge.products.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductDTO productDTO) {
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<ProductDTO> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int linesPerPage,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(defaultValue = "name") String orderBy
    ) {
        return productService.getAllProducts(page, linesPerPage, direction, orderBy);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody @Valid ProductDTO productDTO, @PathVariable(name = "id") Long id) {
        ProductDTO productResponse = productService.updateProduct(productDTO, id);

        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") Long id){
        productService.deleteProductById(id);

        return new ResponseEntity<>("Product entity deleted successfully.", HttpStatus.OK);
    }
}