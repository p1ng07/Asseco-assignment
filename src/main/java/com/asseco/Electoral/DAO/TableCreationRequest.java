package com.asseco.Electoral.DAO;

import jakarta.validation.constraints.NotBlank;

public class TableCreationRequest {
    @NotBlank(message = "Electoral table needs to have a name identifier.")
    private String nameIdentifier;

    @NotBlank(message = "Electoral table needs to have a district identifier.")
    private String district;

    @NotBlank(message = "Electoral table needs to have a municipality identifier.")
    private String municipality;

    public @NotBlank(message = "Electoral table needs to have a name identifier.") String getNameIdentifier() {
        return nameIdentifier;
    }

    public void setNameIdentifier(@NotBlank(message = "Electoral table needs to have a name identifier.") String nameIdentifier) {
        this.nameIdentifier = nameIdentifier;
    }

    public @NotBlank(message = "Electoral table needs to have a district identifier.") String getDistrict() {
        return district;
    }

    public void setDistrict(@NotBlank(message = "Electoral table needs to have a district identifier.") String district) {
        this.district = district;
    }

    public @NotBlank(message = "Electoral table needs to have a municipality identifier.") String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(@NotBlank(message = "Electoral table needs to have a municipality identifier.") String municipality) {
        this.municipality = municipality;
    }
}
