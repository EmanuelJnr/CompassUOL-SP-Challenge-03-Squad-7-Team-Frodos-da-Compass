package br.com.compassuol.pb.challenge.products.service.impl;

import br.com.compassuol.pb.challenge.products.entity.Role;
import br.com.compassuol.pb.challenge.products.exception.ResourceNotFoundException;
import br.com.compassuol.pb.challenge.products.payload.RoleDTO;
import br.com.compassuol.pb.challenge.products.repository.RoleRepository;
import br.com.compassuol.pb.challenge.products.service.RoleService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public RoleDTO createRole(RoleDTO roleDTO) {
        Role role = mapToEntity(roleDTO);

        return mapToDTO(roleRepository.save(role));
    }

    public List<RoleDTO> getAllRole() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(role -> mapToDTO(role)).collect(Collectors.toList());
    }

    public RoleDTO getRoleById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));
        return mapToDTO(role);
    }

    public RoleDTO updateRole(RoleDTO roleDTO, Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));

        role.setName(roleDTO.getName());

        return mapToDTO(roleRepository.save(role));
    }

    public void deleteRoleById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));
        roleRepository.delete(role);
    }

    private RoleDTO mapToDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }

    public Role mapToEntity(RoleDTO roleDTO){
        Role role = new Role();
        role.setName(roleDTO.getName());
        return role;
    }
}