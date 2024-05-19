package com.asseco.Electoral.DAO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class PersonDTO {
    /**
     * Full PersonController of person.
     * It can't be null, empty nor only spaces
     */
    @NotBlank(message = "Person needs to have a full PersonController.")
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

    public @NotBlank(message = "Person needs to have a full PersonController.") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Person needs to have a full PersonController.") String name) {
        this.name = name;
    }

    @Positive(message = "Person needs to have a valid age.")
    public int getAge() {
        return age;
    }

    public void setAge(@Positive(message = "Person needs to have a valid age.") int age) {
        this.age = age;
    }

    public @NotBlank(message = "Person needs to have a citizen card id.") String getCitizenCardId() {
        return citizenCardId;
    }

    public void setCitizenCardId(@NotBlank(message = "Person needs to have a citizen card id.") String citizenCardId) {
        this.citizenCardId = citizenCardId;
    }
}
