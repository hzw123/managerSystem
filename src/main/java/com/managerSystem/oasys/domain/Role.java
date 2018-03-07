package com.managerSystem.oasys.domain;

import com.managerSystem.oasys.enums.AllEnum;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Enumerated(EnumType.ORDINAL)
    private AllEnum status;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(AllEnum status) {
        this.status = status;
    }

    public AllEnum getStatus() {
        return status;
    }
}
