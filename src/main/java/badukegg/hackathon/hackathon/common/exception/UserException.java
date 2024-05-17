package badukegg.hackathon.hackathon.common.exception;


import badukegg.hackathon.hackathon.common.response.ResponseCode;

public class UserException extends BaseException{

    public UserException(ResponseCode responseCode){
        super(responseCode);
    }
}
