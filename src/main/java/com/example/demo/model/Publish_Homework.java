package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "publish_homework")
public class Publish_Homework {
    @Id
    private String ph_id;
    private String user_id;
    private String ph_name;
    private String ph_content;
    private String class_id;
    private String ph_file;
    private String riqi;


    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public String getPh_id() {
        return ph_id;
    }

    public void setPh_id(String ph_id) {
        this.ph_id = ph_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPh_name() {
        return ph_name;
    }

    public void setPh_name(String ph_name) {
        this.ph_name = ph_name;
    }

    public String getPh_content() {
        return ph_content;
    }

    public void setPh_content(String ph_content) {
        this.ph_content = ph_content;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getPh_file() {
        return ph_file;
    }

    public void setPh_file(String ph_file) {
        this.ph_file = ph_file;
    }

    public String getRiqi() {
        return riqi;
    }

    public void setRiqi(String riqi) {
        this.riqi = riqi;
    }
}
