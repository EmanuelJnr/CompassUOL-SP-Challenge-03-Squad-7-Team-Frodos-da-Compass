package br.com.compassuol.pb.challenge.products.repository;

import br.com.compassuol.pb.challenge.products.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}