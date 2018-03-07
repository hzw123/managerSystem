package com.managerSystem.oasys.server.impl;

import com.managerSystem.oasys.domain.User;
import com.managerSystem.oasys.repository.UserRepository;
import com.managerSystem.oasys.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServerImpl implements UserServer{

    @Autowired
    private UserRepository userRepository;

    public User findByName(String name){
        return userRepository.findByName(name);
    }
}
