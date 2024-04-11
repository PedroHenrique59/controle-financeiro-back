package com.pedro.controle_financeiro.mapper;

import com.pedro.controle_financeiro.model.User;
import com.pedro.controle_financeiro.util.EncryptPassword;
import com.pedro.controle_financeiro.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserMapper {

    public User convertVoToEntity(UserVO userVO) {
        User user = new User();
        user.setId(userVO.getId());
        user.setUserName(userVO.getUsername());
        user.setPassword(EncryptPassword.encrypt(userVO.getPassword()));
        user.setPermissions(new ArrayList<>());

        //Sempre atribuir algum valor para essas propriedades, caso passe NULL vai dar erro no authenticate
        user.setEnabled(Boolean.TRUE);
        user.setAccountNonExpired(Boolean.TRUE);
        user.setAccountNonLocked(Boolean.TRUE);
        user.setCredentialsNonExpired(Boolean.TRUE);

        return user;
    }
}
