package com.managerSystem.oasys.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface JpaAll<T,ID extends Serializable> extends JpaRepository<T,ID>,JpaSpecificationExecutor<T>{

}
