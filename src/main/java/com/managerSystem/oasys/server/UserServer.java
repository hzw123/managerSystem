package com.managerSystem.oasys.server;

import com.managerSystem.oasys.domain.User;

public interface UserServer {
    public User findByName(String name);
}
