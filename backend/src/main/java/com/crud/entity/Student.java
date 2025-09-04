package com.crud.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Student {
    @Id
    private String id;
    private String name;
    private int age;
    private String email;
    private long mark;
    private String address;
    private String password;
}
