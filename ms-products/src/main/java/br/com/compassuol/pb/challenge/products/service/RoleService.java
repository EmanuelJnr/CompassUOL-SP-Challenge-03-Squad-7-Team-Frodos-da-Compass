package br.com.compassuol.pb.challenge.products.service;

import br.com.compassuol.pb.challenge.products.DTO.RoleDTO;
import java.util.List;

public interface RoleService {

    RoleDTO createRole(RoleDTO roleDTO);

    List<RoleDTO> getAllRole();

    RoleDTO getRoleById(Long id);

    RoleDTO updateRole(RoleDTO roleDTO, Long id);

    void deleteRoleById(Long id);
}
