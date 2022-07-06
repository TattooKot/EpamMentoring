package org.example.core.model;

import lombok.Data;

@Data
public class User implements UserInterface{
    private long id;
    private String name;
    private String email;
    private Integer phone;

    @Override
    public long getId(){
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }
}
