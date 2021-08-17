package com.example.demo.repository;

import com.example.demo.model.Create_Class;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface Create_ClassRepository extends JpaRepository<Create_Class,String> {
    //public List<Create_Class> findAllByuser_id(String user_id);
}
