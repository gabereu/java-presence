package com.example.presence.domain.service;

import com.example.presence.domain.models.Admin;
import com.example.presence.domain.repository.IAdminRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AdminService {
    
    private IAdminRepository adminRepository;
    
    public Admin login(String username){
        var result = this.adminRepository.findByUsername(username);

        if(result.isPresent()){
            return result.get();
        }

        Admin admin = this.adminRepository.save(new Admin().setUsername(username));
        
        return admin;
    } 
}
