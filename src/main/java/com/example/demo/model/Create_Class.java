package com.example.demo.model;


import javax.persistence.*;


@Entity
@Table(name = "create_class")
public class Create_Class {
    @Id
    private String class_id;

    @Column(name = "user_id")
    private String user_id;
    private String class_name;
    private String class_password;
    private int class_sum;

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getClass_password() {
        return class_password;
    }

    public void setClass_password(String class_password) {
        this.class_password = class_password;
    }

    public int getClass_sum() {
        return class_sum;
    }

    public void setClass_sum(int class_sum) {
        this.class_sum = class_sum;
    }
}
