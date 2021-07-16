package locations;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommand {

    @Schema(description = "name of location", example = "Pécs")
    private String name;
    @Schema(description = "latitude of location", example = "43.7831")
    private double lat;
    @Schema(description = "longitude of location", example = "17.7831")
    private double lon;

}
