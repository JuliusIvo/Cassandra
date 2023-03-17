package com.example.demo;

import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

public interface StudentRepository extends CassandraRepository<Student, String> {

    List<Student> findAllByCourse(String Courses);

    boolean existsById(String id);

    boolean existsByStudentNumber(int StudentNumber);
    Student findByStudentNumber(int studentNumber);
}
