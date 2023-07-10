package br.com.compassuol.pb.challenge.products.repository;

import br.com.compassuol.pb.challenge.products.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
}