package ro.tuc.ds2020.controllers;
import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.services.PersonService;
import ro.tuc.ds2020.services.RestService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/person")
public class PersonController {

    private final PersonService personService;
    private final RestService restService;

    @Autowired
    public PersonController(PersonService personService, RestService restService) {
        this.personService = personService;
        this.restService = restService;
    }

    @GetMapping()
    public ResponseEntity<List<PersonDTO>> getPersons() {
        List<PersonDTO> dtos = personService.findPersons();
        for (PersonDTO dto : dtos) {
            Link personLink = linkTo(methodOn(PersonController.class)
                    .getPerson(dto.getId())).withRel("personDetails");
            dto.add(personLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Integer> insertPerson(@Valid @RequestBody PersonDetailsDTO personDTO) {
        int personID = personService.insert(personDTO);
        //create json object : {id: personID}


        restService.callOtherMicroserviceEndpoint(personID);
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDetailsDTO> getPerson(@PathVariable("id") int personId) {
        PersonDetailsDTO dto = personService.findPersonById(personId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //TODO: UPDATE, DELETE per resource

    @PutMapping(value = "/{id}")
    public ResponseEntity<PersonDetailsDTO> updatePerson(@RequestBody PersonDetailsDTO personDTO, @PathVariable("id") int personId) {
        PersonDetailsDTO dto = personService.update(personDTO, personId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> deletePerson(@PathVariable("id") int personId) {
        personService.delete(personId);
        return new ResponseEntity<>(personId, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<PersonDetailsDTO> login(@RequestBody Map<String, String> requestMap) {
        String email = requestMap.get("email");
        String password = requestMap.get("password");

        PersonDetailsDTO dto = personService.login(email, password);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
