package uz.pdp.apptask1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Apiresponse {
    private String message;
    private Boolean success;
    private Object object;

    public Apiresponse(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }
}
