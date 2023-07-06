package br.com.compassuol.pb.challenge.products.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_category", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();
}
