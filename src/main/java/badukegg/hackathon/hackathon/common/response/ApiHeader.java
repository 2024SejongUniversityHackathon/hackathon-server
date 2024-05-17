package badukegg.hackathon.hackathon.common.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class ApiHeader {
    private int responseCode;
    private HttpStatus status;
    private String message;

    public ApiHeader (ResponseCode responseCode){
        this.responseCode = responseCode.getHttpStatusCode();
        this.status = responseCode.getHttpStatus();
        this.message = responseCode.getMessage();
    }
}
