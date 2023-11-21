package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;
import java.util.UUID;

public class PersonDTO extends RepresentationModel<PersonDTO> {
    private int id;
    private String name;
    private int age;

    private String role;

    private String address;

    private String email;

    private String password;

    public PersonDTO() {
    }

    public PersonDTO(int id, String name, int age, String role, String address, String email, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.role = role;
        this.address = address;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
    	return role;
    }

    public void setRole(String role) {
    	this.role = role;
    }

    public String getAddress() {
    	return address;
    }

    public void setAddress(String address) {
    	this.address = address;
    }

    public String getEmail() {
    	return email;
    }

    public String getPassword() {
    	return password;
    }

    public void setEmail(String email) {
    	this.email = email;
    }

    public void setPassword(String password) {
    	this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return age == personDTO.age &&
                Objects.equals(name, personDTO.name) &&
                Objects.equals(role, personDTO.role)&&
                Objects.equals(address, personDTO.address)&&
                Objects.equals(email, personDTO.email) &&
                Objects.equals(password, personDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, role, address, email, password);
    }
}
