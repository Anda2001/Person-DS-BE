package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.dtos.builders.PersonBuilder;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDTO> findPersons() {
        List<Person> personList = personRepository.findAll();
        return personList.stream()
                .map(PersonBuilder::toPersonDTO)
                .collect(Collectors.toList());
    }

    public PersonDetailsDTO findPersonById(int id) {
        Optional<Person> prosumerOptional = personRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", id);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + id);
        }
        return PersonBuilder.toPersonDetailsDTO(prosumerOptional.get());
    }

    public int insert(PersonDetailsDTO personDTO) {
        Person person = PersonBuilder.toEntity(personDTO);

        person = personRepository.save(person);
        System.out.println("Ppperson"+ person.getId());
        LOGGER.debug("Person with id {} was inserted in db", person.getId());
        System.out.println("Ppperson"+ person.getId());
        return person.getId();
    }

    public PersonDetailsDTO update(PersonDetailsDTO personDTO, int id) {
        Optional<Person> prosumerOptional = personRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", id);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + id);
        }
        Person person = prosumerOptional.get();
        person.setName(personDTO.getName());
        person.setAddress(personDTO.getAddress());
        person.setAge(personDTO.getAge());
        person.setEmail(personDTO.getEmail());
        person.setPassword(personDTO.getPassword());
        person = personRepository.save(person);
        LOGGER.debug("Person with id {} was updated in db", person.getId());
        return PersonBuilder.toPersonDetailsDTO(person);
    }

    public void delete(int id) {
        Optional<Person> prosumerOptional = personRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", id);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + id);
        }
        personRepository.deleteById(id);
        LOGGER.debug("Person with id {} was deleted from db", id);
    }

    public PersonDetailsDTO login(String email, String password) {
        List<Person> personList = personRepository.findAll();
        for (Person person : personList) {
            if (person.getEmail().equals(email) && person.getPassword().equals(password)) {
                return PersonBuilder.toPersonDetailsDTO(person);
            }
        }
        return null;
    }
}
