package uz.pdp.apptask1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.NamedEntityGraph;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NamedEntityGraph
@Data
public class WorkerDto {
    @NotNull(message = "fullName should not be empty")
    private String fullName;
    @NotNull(message = "phoneNumber should not be empty")
    private String phoneNumber;
    @NotNull(message = "departmentId should not be empty")
    private Integer departmentId;
    @NotNull(message = "Street should not be empty")
    private String street;
    @NotNull(message = "homeNumber should not be empty")
    private Integer homeNumber;
}
