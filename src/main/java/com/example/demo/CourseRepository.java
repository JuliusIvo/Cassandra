package com.example.demo;

import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

public interface CourseRepository extends CassandraRepository<Course, String> {
    Course findByName(String name);

    void deleteById(String id);

    boolean existsByNameEquals(String name);

    List<Course> findAllByFaculty(String faculty);

}
