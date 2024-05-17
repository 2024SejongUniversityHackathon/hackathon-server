package badukegg.hackathon.hackathon.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class ApiBody <T>{

    private T data;


    public ApiBody(T data){
        this.data = data;

    }

}
