package ro.tuc.ds2020.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.services.PersonService;

import java.util.List;


@RestController
@CrossOrigin
public class IndexController {


    @GetMapping(value = "/")
    public ResponseEntity<String> getStatus() {
        return new ResponseEntity<>("City APP Service is running...", HttpStatus.OK);
    }

//    @GetMapping(value = "/persons")
//    public ResponseEntity<List<PersonDTO>> getAllPersons() {
//        return personController.getPersons();
//    }
//
//    @PutMapping(value = "/person")
//    public ResponseEntity<String> updatePerson(@RequestBody PersonDetailsDTO person) {
//        // p =personController.insertPerson(person);
//
//        return new ResponseEntity<>("Person updated...", HttpStatus.OK);
//    }

}
