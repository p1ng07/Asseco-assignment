package com.asseco.Electoral.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table
public class ElectoralTable {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Electoral table name needs to have a name identifier.")
    @NotEmpty(message = "Name for electoral table should not be empty.")
    private String nameIdentifier;

    @NotNull(message = "Electoral table locality needs to be defined.")
    private LocalityOfTable locality;

    public Long getId() {
        return id;
    }

    public @NotNull(message = "Electoral table locality needs to be defined.") LocalityOfTable getLocality() {
        return locality;
    }

    public @NotNull(message = "Electoral table name needs to have a name identifier.") @NotEmpty(message = "Name for electoral table should not be empty.") String getNameIdentifier() {
        return nameIdentifier;
    }
}
