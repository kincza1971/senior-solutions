package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private Long id;
    private String name;
    private String taj;
    private LocalDate birth;
    private String city;
    private String address;

    public Person(String name, String taj, LocalDate birth, String city, String address) {
        this.name = name;
        this.taj = taj;
        this.birth = birth;
        this.city = city;
        this.address = address;
    }
}
