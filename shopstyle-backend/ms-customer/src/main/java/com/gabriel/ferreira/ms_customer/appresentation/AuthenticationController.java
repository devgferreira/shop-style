package com.gabriel.ferreira.ms_customer.appresentation;

import com.gabriel.ferreira.ms_customer.domain.model.user.User;
import com.gabriel.ferreira.ms_customer.domain.model.user.login.Login;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/login")
public class AuthenticationController {
    private final AuthenticationManager _authenticationManager;

    public AuthenticationController(AuthenticationManager authenticationManager) {
        _authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid Login login ){
        UsernamePasswordAuthenticationToken userNamePassword = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
        Authentication auth = _authenticationManager.authenticate(userNamePassword);
        return ResponseEntity.ok().build();
    }
}
