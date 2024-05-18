package com.asseco.Electoral.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table
public class ElectoralTable {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Electoral table name needs to have a name identifier.")
    private String nameIdentifier;

    @NotNull
    private District district;

    @NotNull
    private Municipality municipality;

    public @NotNull District getDistrict() {
        return district;
    }

    public void setDistrict(@NotNull District district) {
        this.district = district;
    }

    public @NotNull Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(@NotNull Municipality municipality) {
        this.municipality = municipality;
    }

    public void setNameIdentifier(@NotBlank(message = "Electoral table name needs to have a name identifier.") String nameIdentifier) {
        this.nameIdentifier = nameIdentifier;
    }
    public @NotBlank(message = "Electoral table name needs to have a name identifier.") String getNameIdentifier() {
        return nameIdentifier;
    }
}
