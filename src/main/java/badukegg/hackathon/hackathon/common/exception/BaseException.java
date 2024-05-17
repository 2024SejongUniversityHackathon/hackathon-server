package badukegg.hackathon.hackathon.common.exception;

import badukegg.hackathon.hackathon.common.response.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BaseException extends RuntimeException{

    private final ResponseCode responseCode;

    @Override
    public String getMessage(){
        return responseCode.getMessage();
    }

}
