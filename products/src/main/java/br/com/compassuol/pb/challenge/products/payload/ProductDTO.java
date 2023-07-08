package br.com.compassuol.pb.challenge.products.payload;

import br.com.compassuol.pb.challenge.products.entity.Category;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDTO {
    private Long id;
    private LocalDateTime date;
    private String description;
    private String name;
    private String imgUrl;
    private BigDecimal price;
    private List<Category> categories = new ArrayList<>();
}