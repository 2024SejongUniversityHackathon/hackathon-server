package badukegg.hackathon.hackathon.member.controller;

import badukegg.hackathon.hackathon.common.response.ApiResponseCustom;
import badukegg.hackathon.hackathon.common.response.ResponseCode;
import badukegg.hackathon.hackathon.member.dto.request.ScoreRequest;
import badukegg.hackathon.hackathon.member.dto.response.ScoreResponse;
import badukegg.hackathon.hackathon.member.service.ScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
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
    public ApiResponseCustom<String> saveScores(Principal principal,  @RequestBody ScoreRequest request) {
        String socialId = principal.getName();
        scoreService.saveScores(request, socialId);
        return ApiResponseCustom.success(ResponseCode.SCORE_SUCCESS);
    }

    @GetMapping("/my-scores")
    @Operation(summary = "점수 반환", description = "점수 반환하는 Controller" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "점수 반환 성공"),
            @ApiResponse(responseCode = "500", description = "점수가 정상적으로 반환되지 않았습니다.")
    })
    public ApiResponseCustom<?> saveScores(Principal principal) {
        String socialId = principal.getName();
        ScoreResponse scores = scoreService.getScores(socialId);
        return ApiResponseCustom.success(scores, ResponseCode.SCORE_SUCCESS);
    }


}
