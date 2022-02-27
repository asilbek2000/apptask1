package uz.pdp.apptask1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.NamedEntityGraph;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NamedEntityGraph
@Data
public class AddressDto {
    @NotNull(message = "Street should not be empty")
    private String street;
    @NotNull(message = "homeNumber should not be empty")
    private Integer homeNumber;
}
