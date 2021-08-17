package com.example.demo.dao;

import com.example.demo.model.Publish_Homework;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Publish_HomeworkDao {
    void publish_homework(Publish_Homework publish_homework);
    List<Publish_Homework> findByclass_id(String class_id);
    Publish_Homework findByph_id(String ph_id);
}
