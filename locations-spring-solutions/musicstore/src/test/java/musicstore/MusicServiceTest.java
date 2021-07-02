package musicstore;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class MusicServiceTest {

    private ModelMapper modelMapper = new ModelMapper();

    MusicService musicService = new MusicService(modelMapper);

    @Test
    void testAdd() {
        InstrumentDTO idto = musicService.addInstrument(
                new CreateInstrumentCommand("Fender",InstrumentType.ELECTRIC_GUITAR,2000)
        );
        System.out.println(idto);
    }

}
