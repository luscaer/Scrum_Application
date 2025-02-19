package com.luscaer.Scrum_application.entity;

import com.luscaer.Scrum_application.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotNull
    @Column(nullable = false)
    @Size(min = 2, max = 100, message = "The name must be between 2 and 100 characters.")
    protected String name;

    @NotNull
    @Column(nullable = false)
    @Email(message = "Invalid Email Format.")
    protected String email;

    @NotNull
    @Column(nullable = false)
    @Pattern(regexp = "^\\d{2}9\\d{8}$", message = "Phone number format is invalid.")
    protected String phone;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    protected Gender gender;
}
