package com.managerSystem.oasys.controller;

import com.managerSystem.oasys.domain.User;
import com.managerSystem.oasys.enums.AllEnum;
import com.managerSystem.oasys.repository.UserRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User findOne(@PathVariable Long id){
        return userRepository.findById(id).get();
    }

    @RequestMapping(method = RequestMethod.POST)
    public User addUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    public User updateUser(@PathVariable Long id,@RequestBody User user){
        return userRepository.save(user);
    }

    public Page<User> pageUsers(String name,String roleName,String status,Pageable pageable){

        return userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list=new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(name)){
                   list.add(cb.like(root.get("name"),"%"+name + "%"));
                }

                if(StringUtils.isNotBlank(roleName)){
                   list.add(cb.like(root.join("roles").get("name"),"%"+name + "%"));
                }

                if(StringUtils.isNotBlank(status)){
                    list.add(cb.equal(root.get("status"), AllEnum.getEnum(status)));
                }

                Predicate[] predicates=new Predicate[list.size()];
                query.where(cb.and(list.toArray(predicates)));
                return null;
            }
        },pageable);
    }
}
