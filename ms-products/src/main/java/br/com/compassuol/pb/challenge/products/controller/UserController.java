package br.com.compassuol.pb.challenge.products.controller;

import br.com.compassuol.pb.challenge.products.entity.User;
import br.com.compassuol.pb.challenge.products.DTO.UserDTO;
import br.com.compassuol.pb.challenge.products.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users/")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO userDTO) {
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUser() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid UserDTO userDTO, @PathVariable(name = "id") Long id) {
        UserDTO userResponse = userService.updateUser(userDTO, id);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Long id){
        userService.deleteUserById(id);

        return new ResponseEntity<>("User entity deleted successfully.", HttpStatus.OK);
    }
}