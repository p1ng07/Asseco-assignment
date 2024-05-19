package com.asseco.Electoral.DAO;

import com.asseco.Electoral.Models.District;
import com.asseco.Electoral.Models.Municipality;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TableDTO {
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
