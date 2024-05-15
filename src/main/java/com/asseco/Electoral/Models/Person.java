package com.asseco.Electoral.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

/**
 * Represents a person in the Electoral system.
 */
@Entity
@Table
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Full name of person
     */
    @NotNull(message = "Person needs to have a full name.")
    private String name;

    @NotNull(message = "Person needs to have a defined age.")
    private int age;

    @NotNull(message = "Person needs to have a citizen card id.")
    private String citizenCardId;

    @ManyToOne
    @JoinColumn(name = "electoralTableId")
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
