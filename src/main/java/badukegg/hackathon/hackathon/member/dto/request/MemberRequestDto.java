package badukegg.hackathon.hackathon.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MemberRequestDto {

    private String username;
    private String email;
    private String socialId;
}
