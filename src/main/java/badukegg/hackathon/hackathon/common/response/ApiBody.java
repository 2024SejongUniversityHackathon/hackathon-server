package badukegg.hackathon.hackathon.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiBody <T>{

    private T data;
    private T message;

    public ApiBody(T message){
        this.message = message;
    }

}
