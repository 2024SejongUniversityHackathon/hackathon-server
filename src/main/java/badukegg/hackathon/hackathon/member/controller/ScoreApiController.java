package badukegg.hackathon.hackathon.member.controller;

import badukegg.hackathon.hackathon.common.response.ApiResponseCustom;
import badukegg.hackathon.hackathon.common.response.ResponseCode;
import badukegg.hackathon.hackathon.member.service.ScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScoreApiController {

    private final ScoreService scoreService;

    public ScoreApiController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping("/scores")
    @Operation(summary = "점수 입력", description = "점수 입력하는 Controller" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "점수 입력 성공"),
            @ApiResponse(responseCode = "500", description = "점수가 정상적으로 입력되지 않았습니다.")
    })
    public ApiResponseCustom<String> saveScores(@RequestBody @Schema(example = "[1,2,3,4,5,6]") List<Integer> scores) {
        if (scores.size() != 6) {
            return ApiResponseCustom.fail(ResponseCode.SCORE_ERROR);
        }

        scoreService.saveScores(scores);
        return ApiResponseCustom.success(ResponseCode.SCORE_SUCCESS);
    }

}
