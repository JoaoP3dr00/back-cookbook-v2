package com.back.cookbook.business;

import com.back.cookbook.dataac.entity.UsuarioEntity;
import com.back.cookbook.dataac.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Service
public class UsuarioManager {
    @Autowired(required = true)
    UserRepository userRepository;

    public void criarUsuario(String email, String senha){
        UsuarioEntity usuario = new UsuarioEntity(email, hashPassword(senha));

        userRepository.save(usuario);
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
