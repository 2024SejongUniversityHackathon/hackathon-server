package badukegg.hackathon.hackathon.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class ApiBody <T>{

    private T data;
    private int responseCode;
    private HttpStatus status;
    private String message;

    public ApiBody(ResponseCode responseCode, T data){
        this.data = data;
        this.responseCode = responseCode.getHttpStatusCode();
        this.status = responseCode.getHttpStatus();
        this.message = responseCode.getMessage();
    }
    public ApiBody(ResponseCode responseCode){
        this.responseCode = responseCode.getHttpStatusCode();
        this.status = responseCode.getHttpStatus();
        this.message = responseCode.getMessage();
    }
}
