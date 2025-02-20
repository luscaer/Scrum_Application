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

    @Column(nullable = false)
    protected String name;

    @Column(nullable = false)
    protected String email;

    @Column(nullable = false)
    protected String phone;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    protected Gender gender;
}
