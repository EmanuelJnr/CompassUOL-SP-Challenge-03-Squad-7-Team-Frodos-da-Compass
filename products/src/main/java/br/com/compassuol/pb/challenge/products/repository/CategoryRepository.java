package br.com.compassuol.pb.challenge.products.repository;

import br.com.compassuol.pb.challenge.products.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
