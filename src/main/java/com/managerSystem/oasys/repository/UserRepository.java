package com.managerSystem.oasys.repository;

import com.managerSystem.oasys.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaAll<User,Long>{
    public User findByName(String name);
}
