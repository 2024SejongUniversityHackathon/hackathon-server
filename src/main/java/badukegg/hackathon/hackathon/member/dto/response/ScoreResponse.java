package badukegg.hackathon.hackathon.member.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class ScoreResponse {
    @NotNull
    private int r;
    @NotNull
    private int i;
    @NotNull
    private int a;
    @NotNull
    private int s;
    @NotNull
    private int e;
    @NotNull
    private int c;

}
