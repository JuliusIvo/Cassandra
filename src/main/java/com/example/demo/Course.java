package com.example.demo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Indexed;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;
@Data
@Table("Course")
public class Course {
    @Column
    @PrimaryKey
    private String id;
    @Column
    @Indexed
    private String name;
    @Column
    private int amountOfStudents;

    @Column
    private String faculty;

    @Column
    private int studentCap;

    public Course(String name, String faculty, int studentCap) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.amountOfStudents = 0;
        this.faculty = faculty;
        this.studentCap = studentCap;
    }
}
