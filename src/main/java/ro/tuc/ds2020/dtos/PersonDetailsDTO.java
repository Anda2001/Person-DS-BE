package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.dtos.validators.annotation.AgeLimit;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class PersonDetailsDTO {

    private int id;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @AgeLimit(limit = 18)
    private int age;

    @NotNull
    private String role;

    @NotNull
    private String email;

    private String password;

    public PersonDetailsDTO() {
    }

    public PersonDetailsDTO( String name, String address, int age, String role, String email, String password) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.role = role;
        this.email = email;
        this.password = password;
    }

    public PersonDetailsDTO(int id, String name, String address, int age, String role, String email, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.role = role;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}

    public String getEmail() {return email;}

    public String getPassword() {return password;}

    public void setEmail(String email) {this.email = email;}

    public void setPassword(String password) {this.password = password;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDetailsDTO that = (PersonDetailsDTO) o;
        return age == that.age &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address) &&
                Objects.equals(role, that.role) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, age, role, email, password);
    }
}
