package uz.pdp.apptask1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.NamedEntityGraph;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NamedEntityGraph
@Data
public class DepartmentDto {

    @NotNull(message = "name should not be empty")
    private String name;
    @NotNull(message = "companyId should not be empty")
    private Integer companyId;
}
