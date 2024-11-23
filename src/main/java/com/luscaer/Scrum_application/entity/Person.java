package com.luscaer.Scrum_application.entity;

import com.luscaer.Scrum_application.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@MappedSuperclass
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = ".*\\S.*", message = "Name cannot be blank.")
    private String name;

    @NotNull
    @Email(message = "Invalid Email Format.")
    private String email;

    @NotNull
    @Pattern(regexp = "^\\d{2}9\\d{8}$", message = "Phone number format is invalid.")
    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;
}
