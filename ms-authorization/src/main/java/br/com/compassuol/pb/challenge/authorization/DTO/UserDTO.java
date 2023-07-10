package br.com.compassuol.pb.challenge.authorization.DTO;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<RoleDTO> roles = new ArrayList<>();
}