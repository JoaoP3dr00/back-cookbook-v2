package com.back.cookbook.domain;

import com.back.cookbook.domain.entity.UserEntity;
import com.back.cookbook.dataac.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserManager {
    @Autowired(required = true)
    UserRepository userRepository;

    /**
     * Método para criar usuário que verifica se o email é válido e se já está cadastrado
     * no bd.
     *
     * @param email
     * @param senha
     * @return
     */
    public Boolean criarUsuario(String email, String senha) {
        if (validaEmail(email)){
            if(!userRepository.existsByEmail(email)){
                //UserEntity usuario = new UserEntity(email, hashPassword(senha));
                //userRepository.save(usuario);

                return true;
            }
        }

        return false;
    }

    /**
     * Método
     *
     * @param email
     * @param senha
     * @return
     */
    public Boolean validaUsuario(String email, String senha){
        UserEntity u = userRepository.findByEmail(email);

        if(u != null) {
            if (!validaEmail(email))
                return false;

            if (u.getEmail().equals(email) && u.getSenha().equals(hashPassword(senha)))
                return true;
        }

        return false;
    }

    /**
     * Função só faz validação de regex
     *
     * @param email
     * @return
     */
    public static boolean validaEmail(String email){
        String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";

        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        // Verifica o email usando o matcher
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
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
