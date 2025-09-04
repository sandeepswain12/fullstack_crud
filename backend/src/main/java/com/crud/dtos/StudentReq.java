package com.crud.dtos;


import jakarta.validation.constraints.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentReq {
    @NotBlank(message = "name is required")
    @Size(min = 3,max = 20,message = "name should between 3 to 20")
    private String name;
    @Min(value = 1, message = "age should be at least 1")
    @Max(value = 120, message = "age should not exceed 50")
    private int age;
    @NotBlank(message = "email is required")
    @Email(message = "please enter a valid email")
    private String email;
    @Min(value = 1, message = "mark should be at least 1")
    @Max(value = 600, message = "mark should not exceed 600")
    private long mark;
    @Size(min = 5, max = 1000,message = "address should between 10 to 100")
    private String address;
    @NotBlank(message = "password is required")
    @Size(min = 8, max = 8,message = "password must be 8 letter")
    private String password;
}
