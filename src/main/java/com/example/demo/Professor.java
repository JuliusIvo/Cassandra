package com.example.demo;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Indexed;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;
@Data
@Table("Professor")
public class Professor {
    @Column
    @PrimaryKey
    private String id;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Indexed
    @Column
    private String course;
    @Column
    private String title;

    public Professor(String firstName, String lastName, String course, String title){
        super();
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;
        this.title = title;

    }

}
