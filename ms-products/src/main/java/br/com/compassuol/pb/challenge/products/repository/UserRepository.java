package br.com.compassuol.pb.challenge.products.repository;

import br.com.compassuol.pb.challenge.products.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}