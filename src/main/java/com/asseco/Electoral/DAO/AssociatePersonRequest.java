package com.asseco.Electoral.DAO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class AssociatePersonRequest {
    /**
     * Person id to associate table with.
     * Must be more than or equal to 0
     */
    @PositiveOrZero
    @NotNull
    private long personId;

    /**
     * ElectoralTable id to associate person with.
     * Must be more than or equal to 0
     */
    @PositiveOrZero
    @NotNull
    private long electoralTableId;

    @PositiveOrZero
    public long getPersonId() {
        return personId;
    }

    public void setPersonId(@PositiveOrZero long personId) {
        this.personId = personId;
    }

    @PositiveOrZero
    public long getElectoralTableId() {
        return electoralTableId;
    }

    public void setElectoralTableId(@PositiveOrZero long electoralTableId) {
        this.electoralTableId = electoralTableId;
    }
}
