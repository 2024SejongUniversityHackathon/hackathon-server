package badukegg.hackathon.hackathon.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE)
public enum ResponseCode {

    //400 Bad Request
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    //403 Forbidden
    FORBIDDEN(HttpStatus.FORBIDDEN, "권한이 없습니다."),
    //405 Method Not Allowed
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메소드입니다."),
    //500 Internal Server Error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 오류가 발생하였습니다."),


    /**
     * user response
     */
    //404 Not Found
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다,"),

    //200 OK
    USER_READ_SUCCESS(HttpStatus.OK, "사용자 정보 조회 성공"),
    USER_LOGIN_SUCCESS(HttpStatus.OK, "로그인 성공"),

    //201 Created
    USER_CREATE_SUCCESS(HttpStatus.CREATED, "회원가입 성공"),


    /**
     * document response
     */
    //404 Not Found
    DOCUMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "문서를 찾을 수 없습니다,"),

    //200 OK
    DOCUMENT_READ_SUCCESS(HttpStatus.OK, "문서 업로드 성공");


    private HttpStatus httpStatus;
    private String message;

    public int getHttpStatusCode(){
        return httpStatus.value();
    }

}
