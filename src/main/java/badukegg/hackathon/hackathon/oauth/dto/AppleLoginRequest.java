package badukegg.hackathon.hackathon.oauth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppleLoginRequest {

    private String username;
    private String idToken;
    private String nickname;
    private String email;
    private String birthdate;
    private String socialId;
}
