package br.com.compassuol.pb.challenge.products.controller;

import br.com.compassuol.pb.challenge.products.payload.RoleDTO;
import br.com.compassuol.pb.challenge.products.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/roles/")
public class RoleController {
    private RoleService roleService;
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody @Valid RoleDTO roleDTO) {
        return new ResponseEntity<>(roleService.createRole(roleDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRole() {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getAllRole());
    }

    @GetMapping("{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<RoleDTO> updateRole(@RequestBody @Valid RoleDTO roleDTO, @PathVariable(name = "id") Long id) {
        RoleDTO roleResponse = roleService.updateRole(roleDTO, id);

        return new ResponseEntity<>(roleResponse, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRole(@PathVariable(name = "id") Long id){
        roleService.deleteRoleById(id);

        return new ResponseEntity<>("Role entity deleted successfully.", HttpStatus.OK);
    }
}