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

    @NotBlank(message = "Electoral table needs to have a name identifier.")
    private String nameIdentifier;

    @NotBlank(message = "Electoral table needs to have a district.")
    private String district;

    @NotBlank(message = "Electoral table needs to have a municipality.")
    private String municipality;

    public Long getId() {
        return id;
    }

    public @NotBlank(message = "Electoral table needs to have a name identifier.") String getNameIdentifier() {
        return nameIdentifier;
    }

    public void setNameIdentifier(@NotBlank(message = "Electoral table needs to have a name identifier.") String nameIdentifier) {
        this.nameIdentifier = nameIdentifier;
    }

    public @NotBlank(message = "Electoral table needs to have a district.") String getDistrict() {
        return district;
    }

    public void setDistrict(@NotBlank(message = "Electoral table needs to have a district.") String district) {
        this.district = district;
    }

    public @NotBlank(message = "Electoral table needs to have a municipality.") String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(@NotBlank(message = "Electoral table needs to have a municipality.") String municipality) {
        this.municipality = municipality;
    }
}
