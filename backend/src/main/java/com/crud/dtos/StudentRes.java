package com.crud.dtos;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRes {
    private String id;
    private String name;
    private int age;
    private String email;
    private long mark;
    private String address;
    private String password;
}
