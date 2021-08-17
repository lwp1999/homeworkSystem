package com.example.demo.model;

import javax.persistence.*;


@Entity
@Table(name = "join_class")
public class Join_Class {

    private String user_id;
    @Id
    private String class_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }
}
