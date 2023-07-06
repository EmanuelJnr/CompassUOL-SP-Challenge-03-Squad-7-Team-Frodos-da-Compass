package br.com.compassuol.pb.challenge.products.repository;

import br.com.compassuol.pb.challenge.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
