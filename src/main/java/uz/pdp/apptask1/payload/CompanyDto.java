package uz.pdp.apptask1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.apptask1.entity.Address;

import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NamedEntityGraph
@Data
@NoArgsConstructor
public class CompanyDto {
    @NotNull(message = "name should not be empty")
    private String name;
    @NotNull(message = "director should not be empty")
    private String director;
    @NotNull(message = "Street should not be empty")
    private String street;
    @NotNull(message = "homeNumber should not be empty")
    private Integer homeNumber;
}
