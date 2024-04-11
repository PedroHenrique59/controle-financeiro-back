package com.pedro.controle_financeiro.mapper.custom;

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
        return user;
    }
}
