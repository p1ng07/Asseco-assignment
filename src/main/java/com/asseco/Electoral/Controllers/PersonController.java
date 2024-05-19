package com.asseco.Electoral.Controllers;

import com.asseco.Electoral.DAO.AssociatePersonRequest;
import com.asseco.Electoral.DAO.PersonDTO;
import com.asseco.Electoral.Models.Person;
import com.asseco.Electoral.Repositories.ElectoralTableRepository;
import com.asseco.Electoral.Repositories.PersonRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class PersonController {

    final
    PersonRepository personRepository;

    final
    ElectoralTableRepository tableRepository;

    public PersonController(PersonRepository personRepository, ElectoralTableRepository tableRepository) {
        this.personRepository = personRepository;
        this.tableRepository = tableRepository;
    }

    @GetMapping("/")
    @Operation(summary = "Get all person objects", description = "Retrieve all person objects in database.")
    public List<Person> retrieveAllPeople() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get person with id", description = "Retrieve person object corresponding with the given internal database id.")
    public Person retrievePersonById(@PathVariable long id) {
        Optional<Person> person = personRepository.findById(id);

        // If person with id is not found, return HTTP not found
        if(person.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return person.get();
    }

    @GetMapping("/name={name}")
    @Operation(summary = "Get people with name", description = "Retrieve every person with the given name.")
    public List<Person> retrievePersonByName(@PathVariable String name) {
        return personRepository.findByName(name);
    }

    /**
     * Get {@link Person person} with citizen card id.
     */
    @GetMapping("/citizen_card_id={citizenCardId}")
    @Operation(summary = "Get person with cc id", description = "Retrieve person object corresponding with the given citizen card id.")
    public ResponseEntity<Person> retrievePersonByCCId(@PathVariable String citizenCardId) {
        var personOptional = personRepository.findByCitizenCardId(citizenCardId);

        if(personOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(personOptional.get());
    }

    /**
     * Saves a new unnafiliated (without foreign key to electoralTable) person to the database.
     * Returns a Bad Request when given {@link PersonDTO request} is not
     * valid (see bean validation on {@link PersonDTO request})
     * @param request Person info to save to database
     */
    @PostMapping("/")
    @Operation(summary = "Create person object", description = "Add given person object to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Creation was successful"),
            @ApiResponse(responseCode = "500", description = "Internal error, creation was not successful"),
            @ApiResponse(responseCode = "400", description = "User info is incorrect (age <=0, name is empty, cc id is empty)")
    })
    public void createUnaffiliatedPerson(@Valid @RequestBody PersonDTO request) {
        var person = new Person();
        person.setAge(request.getAge());
        person.setName(request.getName());
        person.setCitizenCardId(request.getCitizenCardId());
        personRepository.save(person);
    }

    /**
     * Deletes a {@link Person Person} object by id.
     * @param id id of Person to delete
     */
    @DeleteMapping("/{id}")
    public void deletePersonById(@PathVariable long id) {
        personRepository.deleteById(id);
    }

    /**
     * Updates the age, PersonController and citizen card id fields of a {@link Person Person}.
     * Returns a Bad Request when given {@link Person Person} is not valid (see bean validation on {@link Person Person})
     * @param dto Person to update
     * @param id id of Person on database to update
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update person object", description = "Update the person object of the given id with the given values")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Updating was successful"),
            @ApiResponse(responseCode = "400", description = "Requested person does not exist.")
    })
    public ResponseEntity<Object> updatePersonById(@Valid @RequestBody PersonDTO dto, @PathVariable long id) {
        Optional<Person> personOptional = personRepository.findById(id);

        // If there isn't a person with given id, return 404
        if(personOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Person personToUpdate = personOptional.get();
        personToUpdate.setName(dto.getName());
        personToUpdate.setAge(dto.getAge());
        personToUpdate.setCitizenCardId(dto.getCitizenCardId());

        personRepository.save(personToUpdate);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/associate/")
    @Operation(summary = "Associate person with table", description = "Associate the person of the given id with the table of the given id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Association was successful"),
            @ApiResponse(responseCode = "400", description = "Requested person or table does not exist.")
    })
    public ResponseEntity<Object> associatePerson(@Valid @RequestBody AssociatePersonRequest request) {
        var table = tableRepository.findById(request.getElectoralTableId());
        var person = personRepository.findById(request.getPersonId());

        if(table.isEmpty() || person.isEmpty()) {
            return ResponseEntity.badRequest().body("Requested person or table does not exist.");
        }

        var personToAssociate = person.get();

        personToAssociate.setElectoralTable(table.get());
        personRepository.save(personToAssociate);

        return ResponseEntity.ok().body("Association was successful");
    }
}
