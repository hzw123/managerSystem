package com.managerSystem.oasys.enums;

import org.apache.commons.lang.StringUtils;

public enum AllEnum {
    USING(0,"start"),
    STOP(1,"stop");
    private int code;
    private String name;

    AllEnum(int code, String name){
        this.code=code;
        this.name=name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return this.name;
    }

    public static AllEnum getEnum(String status){

        for (AllEnum em:AllEnum.values()) {
            if(em.toString().equals(status) ||
                    em.getName().equals(status) ||
                    status.equals(String.valueOf(em.getCode()))){
                return em;
            }
        }
        return null;
    }
}
