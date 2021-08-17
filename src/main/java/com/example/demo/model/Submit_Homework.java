package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name="submit_homework")
public class Submit_Homework {

    @Id
    private String sub_id;

    private String ph_id;
    private String user_id;
    private String class_id;
    private String sub_content;
    private String sub_file;
    private String sub_dat;
    private String user_name;


    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

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

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getSub_content() {
        return sub_content;
    }

    public void setSub_content(String sub_content) {
        this.sub_content = sub_content;
    }

    public String getSub_file() {
        return sub_file;
    }

    public void setSub_file(String sub_file) {
        this.sub_file = sub_file;
    }

    public String getSub_dat() {
        return sub_dat;
    }

    public void setSub_dat(String sub_dat) {
        this.sub_dat = sub_dat;
    }
}
