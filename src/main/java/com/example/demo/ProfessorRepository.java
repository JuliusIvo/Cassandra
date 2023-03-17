package com.example.demo;

import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

public interface ProfessorRepository extends CassandraRepository<Professor, String> {
    Professor findByFirstName(String name);

    boolean existsByCourseEquals(String course);

    void deleteById(String Id);

    List<Professor> findAllByTitle(String Title);

}
