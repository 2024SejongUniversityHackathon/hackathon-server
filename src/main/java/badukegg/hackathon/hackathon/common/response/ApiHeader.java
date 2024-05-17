package badukegg.hackathon.hackathon.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiHeader {
    private int resultCode;
    private String message;

}
