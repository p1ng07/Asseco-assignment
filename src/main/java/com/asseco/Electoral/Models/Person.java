package com.asseco.Electoral.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Represents a person in the Electoral system.
 */
@Entity
@Table
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    public void setName(@NotNull(message = "Person needs to have a full name.") String name) {
        this.name = name;
    }

    public void setAge(@NotNull(message = "Person needs to have a defined age.") int age) {
        this.age = age;
    }

    public void setCitizenCardId(@NotNull(message = "Person needs to have a citizen card id.") String citizenCardId) {
        this.citizenCardId = citizenCardId;
    }

    /**
     * Full name of person.
     * It can't be null, empty nor only spaces
     */
    @NotBlank(message = "Person needs to have a full name.")
    private String name;

    /**
     * Age of person.
     * It can't be less than or equal to 0.
     */
    @Positive(message = "Person needs to have a valid age.")
    private int age;

    /**
     * Cititen card id of person.
     * It can't be null, empty nor only spaces.
     */
    @NotBlank(message = "Person needs to have a citizen card id.")
    private String citizenCardId;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private ElectoralTable electoralTable;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCitizenCardId() {
        return citizenCardId;
    }

    public Long getId() {
        return id;
    }
}
