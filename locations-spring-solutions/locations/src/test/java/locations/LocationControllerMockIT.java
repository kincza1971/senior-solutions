package locations;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
public class LocationControllerMockIT {

    @MockBean
    LocationsService locationsService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testLocationList() throws Exception {

        when(locationsService.getLocations(any())).thenReturn(
                List.of(
                        new LocationDTO("Budapest", 43.1154, 19.5547),
                        new LocationDTO("Debrecen", 43.654, 17.5548)
                )
        );

        mockMvc.perform(get("/api/locations"))
                .andExpect(jsonPath("$[0].name" ,equalTo("Budapest")))
                .andExpect((jsonPath("$[1].name" ,equalTo("Debrecen"))));
    }

    @Test
    void testFindLocationById() throws Exception {
        when(locationsService.findLocationById(any()))
                .thenReturn(new LocationDTO("Budapest", 43.1154, 19.5547)
                );

        mockMvc.perform(get("/api/locations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", equalTo("Budapest")));
    }




}
