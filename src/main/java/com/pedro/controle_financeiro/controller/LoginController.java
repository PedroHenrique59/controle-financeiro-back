package com.pedro.controle_financeiro.controller;

import com.pedro.controle_financeiro.jwt.JwtTokenProvider;
import com.pedro.controle_financeiro.repository.UserRepository;
import com.pedro.controle_financeiro.service.LoginService;
import com.pedro.controle_financeiro.vo.AccountCredentialsVO;
import com.pedro.controle_financeiro.vo.TokenVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/login")
public class LoginController {

    private final LoginService loginService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping(value = "/signin")
    public ResponseEntity<?> signin(@RequestBody AccountCredentialsVO data) {
        if (!checkIfParamsIsNotNull(data)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        } else {
            var token = loginService.signin(data);
            if (token == null) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
            }
            return token;
        }
    }

    @PostMapping(value = "/validateToken")
    private ResponseEntity<Boolean> validateToken(@RequestBody String token) {
        if(Objects.nonNull(token)){
            if(jwtTokenProvider.validateToken(token)) {
                return ResponseEntity.ok(true);
            }
        }
        return ResponseEntity.ok(false);
    }

    private boolean checkIfParamsIsNotNull(AccountCredentialsVO data) {
        return Objects.nonNull(data) && Objects.nonNull(data.getUsername()) && Objects.nonNull(data.getPassword());
    }
}
