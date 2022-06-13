package com.example.readcsvfile.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@Table(name = "csv_read")
public class ReadCsvFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(min = 3,message ="User Name should be only on characters")
    private String name;

    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
            message="Mobile number is invalid")
    private Long phoneNumber;

    @Email
    @NotEmpty(message = "Email is required")
    @Pattern(regexp = "^(.+)@(.+)$",message = "Email is invalid")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ReadCsvFile(Long id, String name, Long phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
