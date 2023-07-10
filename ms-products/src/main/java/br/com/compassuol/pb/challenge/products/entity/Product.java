package br.com.compassuol.pb.challenge.products.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_product", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date", nullable = false)
    private LocalDateTime date;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "imgUrl", nullable = false)
    private String imgUrl;
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_product_category",
        joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private List<Category> categories = new ArrayList<>();
}