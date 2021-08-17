package com.example.demo.dao;

import com.example.demo.model.Submit_Homework;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Submit_HomeworkDao {


//    //学生根据课程找到老师布置的作业
//    List<Publish_Homework> findByuser_class_id(@Param("String class_id") String class_id);

    //提交作业
    void submit_homework(Submit_Homework submit_homework);

    //修改作业
    void cover_homework(Submit_Homework submit_homework);

    //查找作业 根据作业id和用户id
    Submit_Homework findhomework(String ph_id,String user_id);

    List<Submit_Homework> findbyph_id(String ph_id);

}
