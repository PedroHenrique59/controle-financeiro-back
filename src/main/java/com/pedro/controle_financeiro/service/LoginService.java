package com.pedro.controle_financeiro.service;

import com.pedro.controle_financeiro.jwt.JwtTokenProvider;
import com.pedro.controle_financeiro.repository.UserRepository;
import com.pedro.controle_financeiro.util.EncryptPassword;
import com.pedro.controle_financeiro.vo.AccountCredentialsVO;
import com.pedro.controle_financeiro.vo.TokenVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final JwtTokenProvider tokenProvider;

    public ResponseEntity<TokenVO> signin(AccountCredentialsVO data) {
        try {

            var username = data.getUsername();
            var password = EncryptPassword.encrypt(data.getPassword());

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = userRepository.findByUsername(data.getUsername());

            var tokenResponse = new TokenVO();

            if (user != null) {
                tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
            }

            return ResponseEntity.ok(tokenResponse);

        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }

}
