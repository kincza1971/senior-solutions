package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonService {

    private ModelMapper modelMapper;
    private AtomicLong idGenerator = new AtomicLong();

    private List<Person> personList = new ArrayList<>(List.of(
            new Person(idGenerator.incrementAndGet(), "Balogh Béláné", "123456789", LocalDate.of(1970,3,5),"Érd","Őz u. 15"),
            new Person(idGenerator.incrementAndGet(), "Jon Doe", "123456789", LocalDate.of(1970,3,5),"Ózd","Ó u. 15")
    ));

    public PersonService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PersonDto findByTaj(String taj) {
        return modelMapper.map(personList.stream()
                .filter(p -> p.getTaj().equals(taj))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Cannot find person with this TAJ: " + taj))
                ,PersonDto.class);
    }
}
