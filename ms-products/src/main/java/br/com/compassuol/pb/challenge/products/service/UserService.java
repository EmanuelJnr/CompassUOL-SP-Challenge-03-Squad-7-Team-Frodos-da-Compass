package br.com.compassuol.pb.challenge.products.service;

import br.com.compassuol.pb.challenge.products.entity.User;
import br.com.compassuol.pb.challenge.products.DTO.UserDTO;
import java.util.List;

public interface UserService {
    User createUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO updateUser(UserDTO userDTO, Long id);

    void deleteUserById(Long id);
}