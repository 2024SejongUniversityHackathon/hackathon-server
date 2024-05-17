package badukegg.hackathon.hackathon.oauth.controller;

import badukegg.hackathon.hackathon.member.dto.request.MemberRequestDto;
import badukegg.hackathon.hackathon.oauth.common.CustomEntity;
import badukegg.hackathon.hackathon.oauth.dto.AppleLoginRequest;
import badukegg.hackathon.hackathon.oauth.dto.TokenDto;
import badukegg.hackathon.hackathon.oauth.service.AuthService;
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

    @PostMapping("/login/oauth2/code/apple")
    public ResponseEntity<?> callback(@RequestBody AppleLoginRequest appleLoginRequest) throws Exception {
        try {
            LocalDate birthdate = LocalDate.parse(appleLoginRequest.getBirthdate());

            MemberRequestDto memberRequestDto = MemberRequestDto.builder()
                    .email(appleLoginRequest.getEmail())
                    .socialId(appleLoginRequest.getSocialId())
                    .birthdate(birthdate)
                    .username(appleLoginRequest.getUsername())
                    .build();

            TokenDto tokenDto = authService.login(memberRequestDto);

            return ResponseEntity.ok().body(new CustomEntity("Success", "accessToken: " + tokenDto.getAccessToken()));

        }catch (Exception e){
            log.error("Apple 소셜 로그인 오류: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomEntity("Error", "Apple 소셜 로그인 오류"));
        }
    }


}
