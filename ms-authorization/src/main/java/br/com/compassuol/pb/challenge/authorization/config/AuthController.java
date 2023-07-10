package br.com.compassuol.pb.challenge.authorization.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@RestController
public class AuthController {
    @GetMapping("/auth")
    public ResponseEntity<String> printOk(){
        return new ResponseEntity<>("Enter on system: " + LocalDateTime.now(), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")//isso permite que apenas o Admin crie algo no banco
    @PostMapping("/auth")
    public ResponseEntity<String> createPost(@RequestBody String message){
        return new ResponseEntity<>("Saved: " + message +" "+ LocalDateTime.now(), HttpStatus.CREATED);
    }
}