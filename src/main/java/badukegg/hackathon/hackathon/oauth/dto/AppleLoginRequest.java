package badukegg.hackathon.hackathon.oauth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppleLoginRequest {

    private String username;
    private String email;
    private String authorizationCode;
}
