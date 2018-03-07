package com.managerSystem.oasys.domain;

import com.managerSystem.oasys.enums.AllEnum;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "用户名不能为空")
    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false)
    @NotEmpty(message = "密码不能为空")
    private String passWord;
    private String phone;
    private String email;
    private Date createAt;
    private Date updateAt;
    @Enumerated(EnumType.ORDINAL)
    private AllEnum status;
    @ManyToMany
    private Set<Role> roles;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public AllEnum getStatus() {
        return status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public void setStatus(AllEnum status) {
        this.status = status;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
