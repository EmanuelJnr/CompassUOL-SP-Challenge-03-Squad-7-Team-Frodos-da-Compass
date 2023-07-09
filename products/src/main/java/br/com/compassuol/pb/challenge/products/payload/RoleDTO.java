package br.com.compassuol.pb.challenge.products.payload;

import br.com.compassuol.pb.challenge.products.entity.User;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class RoleDTO {
    private Long id;
    private String name;
    private List<User> users = new ArrayList<>();
}