package br.com.compassuol.pb.challenge.products.payload;

import br.com.compassuol.pb.challenge.products.entity.Role;
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
    private List<Role> roles = new ArrayList<>();
}