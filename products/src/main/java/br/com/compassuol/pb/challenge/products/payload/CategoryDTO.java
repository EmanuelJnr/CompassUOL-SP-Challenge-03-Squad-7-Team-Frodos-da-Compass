package br.com.compassuol.pb.challenge.products.payload;

import br.com.compassuol.pb.challenge.products.entity.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private List<Product> products = new ArrayList<>();
}
