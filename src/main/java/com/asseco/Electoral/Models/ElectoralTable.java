package com.asseco.Electoral.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table
public class ElectoralTable {
    @Id
    @GeneratedValue
    @JsonSerialize
    private Long id;

    @NotBlank(message = "Electoral table PersonController needs to have a PersonController identifier.")
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

    public void setNameIdentifier(@NotBlank(message = "Electoral table PersonController needs to have a PersonController identifier.") String nameIdentifier) {
        this.nameIdentifier = nameIdentifier;
    }
    public @NotBlank(message = "Electoral table PersonController needs to have a PersonController identifier.") String getNameIdentifier() {
        return nameIdentifier;
    }
}
