package locations;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommand {
    @NotBlank
    @Schema(description = "name of location", example = "Pécs")
    private String name;
    @Range(min = -90, max = 90, message = "latitude acceptable range is (-90,+90)")
    @Schema(description = "latitude of location", example = "43.7831")
    private double lat;
    @Range(min = -180, max = 180, message = "longitude acceptable range is (-90,+90)")
    @Schema(description = "longitude of location", example = "17.7831")
    private double lon;

}
