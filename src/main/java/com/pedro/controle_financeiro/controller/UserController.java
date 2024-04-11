package com.pedro.controle_financeiro.controller;

import com.pedro.controle_financeiro.mapper.UserMapper;
import com.pedro.controle_financeiro.model.User;
import com.pedro.controle_financeiro.repository.UserRepository;
import com.pedro.controle_financeiro.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user")
public class UserController {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @PostMapping(value = "/createAccount")
    public ResponseEntity<?> createAccount(@RequestBody UserVO data) {
        if (Objects.isNull(data)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        } else {
            User createdUser = userRepository.save(userMapper.convertVoToEntity(data));
            return ResponseEntity.ok(createdUser);
        }
    }

}
