package badukegg.hackathon.hackathon.common.response;

import org.springframework.http.HttpStatus;

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
