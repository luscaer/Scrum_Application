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
    protected Long id;

    @NotNull
    @Column(nullable = false)
    @Pattern(regexp = ".*\\S.*", message = "Name cannot be blank.")
    protected String name;

    @NotNull
    @Column(nullable = false)
    @Email(message = "Invalid Email Format.")
    protected String email;

    @NotNull
    @Column(nullable = false)
    @Pattern(regexp = "^\\d{2}9\\d{8}$", message = "Phone number format is invalid.")
    protected String phone;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    protected Gender gender;
}
