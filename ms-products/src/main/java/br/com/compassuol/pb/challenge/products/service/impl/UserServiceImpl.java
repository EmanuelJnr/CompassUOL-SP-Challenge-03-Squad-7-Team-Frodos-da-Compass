package br.com.compassuol.pb.challenge.products.service.impl;

import br.com.compassuol.pb.challenge.products.entity.Role;
import br.com.compassuol.pb.challenge.products.entity.User;
import br.com.compassuol.pb.challenge.products.exception.ResourceNotFoundException;
import br.com.compassuol.pb.challenge.products.payload.RoleDTO;
import br.com.compassuol.pb.challenge.products.payload.UserDTO;
import br.com.compassuol.pb.challenge.products.repository.UserRepository;
import br.com.compassuol.pb.challenge.products.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleServiceImpl roleServiceImpl;

    public UserServiceImpl(UserRepository userRepository, RoleServiceImpl roleServiceImpl){
        this.userRepository = userRepository;
        this.roleServiceImpl = roleServiceImpl;
    }

    public User createUser(UserDTO userDTO) {
        return userRepository.save(mapToEntity(userDTO));
    }


    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> mapToDTO(user)).collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return mapToDTO(user);
    }

    public UserDTO updateUser(UserDTO userDTO, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

        return mapToDTO(userRepository.save(user));
    }

    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);
    }

    private UserDTO mapToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPassword(user.getPassword());

        for(Role rdto : user.getRoles()){
            RoleDTO roleDTO = roleServiceImpl.getRoleById(rdto.getId());
            Role role = new Role();
            role.setName(roleDTO.getName());
            role.setId(roleDTO.getId());
            userDTO.getRoles().add(role);
        }

        return userDTO;
    }

    private User mapToEntity(UserDTO userDTO){
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

        for(Role rdto : userDTO.getRoles()){
            RoleDTO roleDTO = roleServiceImpl.getRoleById(rdto.getId());
            Role role = new Role();
            role.setName(roleDTO.getName());
            role.setId(roleDTO.getId());
            user.getRoles().add(role);
        }

        return user;
    }
}