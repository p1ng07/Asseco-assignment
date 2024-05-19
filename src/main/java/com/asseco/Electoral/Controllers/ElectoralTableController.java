package com.asseco.Electoral.Controllers;

import com.asseco.Electoral.DAO.TableCreationRequest;
import com.asseco.Electoral.Models.ElectoralTable;
import com.asseco.Electoral.Repositories.ElectoralTableRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/tables")
@Validated
public class ElectoralTableController {

    @Autowired
    ElectoralTableRepository tableRepo;

    @GetMapping("/")
    @Operation(summary = "Get all electoral tables.", description = "Retrieve information of all existing electoral tables.")
    public List<ElectoralTable> retrieveAllTables() {
        return tableRepo.findAll();
    }

    @GetMapping("/district={district}")
    @Operation(summary = "Filter electoral tables by district.",
            description = "Get all existing electoral tables in given district.")
    public List<ElectoralTable> retrieveAllTablesByDistrict(@PathVariable String district) {
        return tableRepo.findByDistrict(district);
    }

    @GetMapping("/municipality={municipality}")
    @Operation(summary = "Filter electoral tables by municipality.",
            description = "Get all existing electoral tables in given municipality.")
    public List<ElectoralTable> retrieveAllTablesByMunicipality(@PathVariable String municipality) {
        return tableRepo.findByMunicipality(municipality);
    }

    /**
     * Get Person object by id from database
     * @param id id of person
     * @return Person object if found
     */
    @GetMapping("/{id}")
    @Operation(summary = "Filter electoral tables by id.",
            description = "Get electoral table corresponding with given id")
    public ElectoralTable retrieveTableById(@PathVariable long id) {
        var table = tableRepo.findById(id);

        // If person with id is not found, return HTTP not found
        if(table.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return table.get();
    }

    /**
     * Saves a new {@link ElectoralTable table} to the database.
     * Returns a Bad Request when given {@link ElectoralTable Person} is not
     * valid (see bean validation on {@link ElectoralTable Person})
     * @param request Table info to save to database
     * @return id of created table
     */
    @PostMapping("/")
    @Operation(summary = "Create table and return database id.", description = "Create electoral table with given information. District and municipality will be turned into lower case before saving to database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Creation successful, link of created table is returned."),
            @ApiResponse(responseCode = "400", description = "Inserted fields are not valid.")
    })
    public long createTable(@Valid @RequestBody TableCreationRequest request) {
        var table = tableFromCreationRequest(request);

        var a = tableRepo.save(table);
        return a.getId();
    }

    /**
     * Deletes a electoralTable object by id
     * @param id id of table to delete
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete electoral tables by id.",
            description = "Delete electoral table corresponding with given id")
    public void deleteElectoralTableById(@PathVariable long id) {
        tableRepo.deleteById(id);
    }

    /**
     * Updates the age, PersonController and citizen card id fields of a {@link ElectoralTable Table}.
     * Returns a Bad Request when given {@link ElectoralTable Table} is not valid (see bean validation on {@link ElectoralTable Table})
     * @param dto Table to update
     * @param id Id of table on database to update
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update electoral table.",
            description = "Update the electoral table of the given id with the given values.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Updating was successful"),
            @ApiResponse(responseCode = "404", description = "Requested table does not exist."),
            @ApiResponse(responseCode = "400", description = "Given table to update is invalid.")
    })
    public ResponseEntity<Object> updateTableById(@Valid @RequestBody TableCreationRequest dto, @PathVariable long id) {
        var tableOptional = tableRepo.findById(id);

        // If there isn't a person with given id, return 404
        if(tableOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ElectoralTable tableToUpdate = tableOptional.get();

        tableToUpdate.setDistrict(dto.getDistrict().toLowerCase().trim());
        tableToUpdate.setMunicipality(dto.getMunicipality().toLowerCase().trim());
        tableToUpdate.setNameIdentifier(dto.getNameIdentifier().trim());

        tableRepo.save(tableToUpdate);

        return ResponseEntity.noContent().build();
    }

    private ElectoralTable tableFromCreationRequest(TableCreationRequest request){
        ElectoralTable table = new ElectoralTable();
        table.setDistrict(request.getDistrict().toLowerCase().trim());
        table.setMunicipality(request.getMunicipality().toLowerCase().trim());
        table.setNameIdentifier(request.getNameIdentifier().trim());
        return table;
    }
}
