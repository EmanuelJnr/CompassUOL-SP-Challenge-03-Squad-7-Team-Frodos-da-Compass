package br.com.compassuol.pb.challenge.authorization.security;

import br.com.compassuol.pb.challenge.authorization.DTO.UserDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private List<UserDTO> userDTOList = getAllUsers();

    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        for (UserDTO userDTO : userDTOList) {
            if (userDTO.getEmail().equals(userEmail)) {
                Set<GrantedAuthority> authorities = userDTO
                        .getRoles()
                        .stream()
                        .map((roleDTO -> new SimpleGrantedAuthority(roleDTO.getName()))).collect(Collectors.toSet());
                return new User(userDTO.getEmail(), userDTO.getPassword(), authorities);
            }
        }
        return null;
    }

    public List<UserDTO> getAllUsers() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<UserDTO>> userResponse = restTemplate.exchange("http://localhost:8081/users/",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<UserDTO>>(){});
        return userResponse.getBody();
    }
}