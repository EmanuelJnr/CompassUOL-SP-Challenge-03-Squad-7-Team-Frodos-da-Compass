package br.com.compassuol.pb.challenge.authorization.DTO;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class RoleDTO {
    private Long id;
    private String name;
    private List<UserDTO> users = new ArrayList<>();
}