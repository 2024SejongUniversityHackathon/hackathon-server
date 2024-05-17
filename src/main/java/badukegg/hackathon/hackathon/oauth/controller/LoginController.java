package badukegg.hackathon.hackathon.oauth.controller;

import badukegg.hackathon.hackathon.common.response.ApiResponseCustom;
import badukegg.hackathon.hackathon.common.response.ResponseCode;
import badukegg.hackathon.hackathon.member.dto.request.MemberRequestDto;
import badukegg.hackathon.hackathon.oauth.common.CustomEntity;
import badukegg.hackathon.hackathon.oauth.dto.AppleLoginRequest;
import badukegg.hackathon.hackathon.oauth.dto.TokenDto;
import badukegg.hackathon.hackathon.oauth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final AuthService authService;

    @Operation(summary = "회원가입 및 로그인", description = "회원가입 및 로그인 진행")
    @ApiResponses(value =  {
            @ApiResponse(responseCode = "200", description = "회원가입 성공")
    })
    @PostMapping("/login/oauth2/code/apple")
    public ApiResponseCustom<?> callback(@RequestBody AppleLoginRequest appleLoginRequest) throws Exception {
        try {
            MemberRequestDto memberRequestDto = MemberRequestDto.builder()
                    .email(appleLoginRequest.getEmail())
                    .socialId(appleLoginRequest.getAuthorizationCode())
                    .username(appleLoginRequest.getUsername())
                    .build();

            TokenDto tokenDto = authService.login(memberRequestDto);

            return ApiResponseCustom.success(tokenDto, ResponseCode.USER_CREATE_SUCCESS);

        }catch (Exception e){
            log.error("Apple 소셜 로그인 오류: {}", e.getMessage());
            return ApiResponseCustom.fail(ResponseCode.TOKEN_EXPIRATION);
        }
    }
//    @Operation(summary = "로그아웃", description = "현재 사용자 로그아웃 처리")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "로그아웃 성공"),
//            @ApiResponse(responseCode = "500", description = "서버에 오류가 발생했습니다.")
//    })
//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(@RequestHeader("Authorization") String accessToken) {
//        try {
//          //  authService.logout(accessToken);
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            log.error("로그아웃 처리 중 오류 발생: {}", e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }


}
