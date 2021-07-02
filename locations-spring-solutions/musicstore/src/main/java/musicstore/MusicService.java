package musicstore;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;

@Service

public class MusicService {

    private List<Instrument> instruments = new ArrayList<>();

    private AtomicLong idGenerator = new AtomicLong();

    private ModelMapper modelMapper;

    public MusicService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public InstrumentDTO addInstrument(CreateInstrumentCommand command) {
        Instrument instrument = new Instrument(idGenerator.incrementAndGet()
                , command.getBrand()
                , command.getType()
                , command.getPrice()
                , LocalDate.now()
        );
        instruments.add(instrument);
        return modelMapper.map(instrument, InstrumentDTO.class);
    }

    public void deleteAllInstrument() {
            idGenerator = new AtomicLong();
            instruments.clear();
    }

    private Instrument foundByID(long id) {
        return instruments
                .stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Instrument not found with this ID: " + id));
    }

    public InstrumentDTO findInstrumentById(long id) {
        Instrument found = foundByID(id);
        return modelMapper.map(found, InstrumentDTO.class);
    }

    public InstrumentDTO updatePriceById(long id, UpdatePriceCommand command) {
        Instrument instrument = foundByID(id);
        if (instrument.getPrice() != command.getNewPrice()) {
            instrument.setPrice(command.getNewPrice());
            instrument.setPostDate(LocalDate.now());
        }
        return modelMapper.map(instrument, InstrumentDTO.class);
    }

    public void deleteInstrumentById(long id) {
        Instrument found = foundByID(id);
        instruments.remove(found);
    }

    public List<InstrumentDTO> listInstruments(Optional<String> brand, Optional<Integer> price) {
        return instruments.stream()
                .filter(i-> brand.isEmpty()|| i.getBrand().equalsIgnoreCase(brand.get()))
                .filter(i-> price.isEmpty() || i.getPrice().equals(price.get()))
                .map(i -> modelMapper.map(i,InstrumentDTO.class))
                .toList();
//        Type targetType = new TypeToken<List<InstrumentDTO>>(){}.getType();
//        return modelMapper.map(filtered, targetType);
    }

}

//Valósítsd meg a MusicStoreServie osztályt, mely egy listában tárolja a hangszereket. Ez a lista kezdetben üres.
// Ez az osztály felelős az id kiosztásért is amikor új elem érkezik.
//
//A MusicController osztálynak a következő funkciókat kell megvalósítania:
//
