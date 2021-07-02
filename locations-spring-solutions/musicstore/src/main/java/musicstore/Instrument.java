package musicstore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instrument {
    private Long id;
    private String brand;
    private InstrumentType type;
    private Integer price;
    private LocalDate postDate;
}

//Feladat
//Ebben a feladatban egy hangszeráruház online webshopalkalmazás backend részét kell megvalósítanod.
//
//Az alap entitás az Instrument melynek van egy egyedi azonosítója, egy márkája, egy típusa, egy ára, és egy közzététel dátuma. Kritériumok:
//
//A típus enum legyen, melyben a következő értékek lehetnek : ELECTRIC_GUITAR, ACOUSTIC_GUITAR, PIANO
//A közzététel dátuma LocalDate
