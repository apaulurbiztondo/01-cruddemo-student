package com.allancode.cruddemo.dao;

import com.allancode.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student student);
    void update(Student student);
    void delete(int id);
    int deleteAll();
    Student findById(Integer id);
    List<Student> findAll();
    List<Student> findByLastName(String lastName);

}
