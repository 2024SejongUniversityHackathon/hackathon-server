package badukegg.hackathon.hackathon.common.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ApiResponseCustom<T>{

    private ApiBody<T> body;

    public ApiResponseCustom(ApiBody body){
        this.body = body;
    }


    public static <T> ApiResponseCustom<T> success(T data, ResponseCode responseCode) {
        return new ApiResponseCustom<T>(new ApiBody<>(responseCode, data));
    }
    public static <T> ApiResponseCustom<T> success(ResponseCode responseCode) {
        return new ApiResponseCustom<T>( new ApiBody<>(responseCode));
    }
    public static <T> ApiResponseCustom<T> create(T data, ResponseCode responseCode) {
        return new ApiResponseCustom<T>(new ApiBody<>(responseCode, data));
    }
    public static <T> ApiResponseCustom<T> fail(ResponseCode responseCode) {
        return new ApiResponseCustom<T>(new ApiBody<>(responseCode));
    }
}
