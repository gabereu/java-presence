package com.example.presence.domain.service;

import com.example.presence.domain.models.User;
import com.example.presence.domain.repository.IUserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService {
    
    private IUserRepository userRepository;
    
    public User login(String cpf){
        var result = this.userRepository.findByCpf(cpf);

        if(result.isPresent()){
            return result.get();
        }

        User user = this.userRepository.save(new User().setCpf(cpf));
        
        return user;
    } 
}
