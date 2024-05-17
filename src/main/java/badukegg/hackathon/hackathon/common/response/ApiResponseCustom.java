package badukegg.hackathon.hackathon.common.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ApiResponseCustom<T>{

    private ApiHeader header;
    private ApiBody<T> body;

    public ApiResponseCustom(ApiHeader header, ApiBody body){
        this.header = header;
        this.body = body;
    }

    public ApiResponseCustom(ApiHeader header){
        this.header = header;
    }
    public static <T> ApiResponseCustom<T> success(T data, ResponseCode responseCode) {
        return new ApiResponseCustom<T>(new ApiHeader(responseCode), new ApiBody<>(data));
    }
    public static <T> ApiResponseCustom<T> success(ResponseCode responseCode) {
        return new ApiResponseCustom<T>( new ApiHeader(responseCode));
    }
    public static <T> ApiResponseCustom<T> create(T data, ResponseCode responseCode) {
        return new ApiResponseCustom<T>(new ApiHeader(responseCode), new ApiBody<>(data));
    }
    public static <T> ApiResponseCustom<T> fail(ResponseCode responseCode) {
        return new ApiResponseCustom<T>(new ApiHeader(responseCode));
    }
}
