package badukegg.hackathon.hackathon.member.controller;

import badukegg.hackathon.hackathon.common.response.ApiResponseCustom;
import badukegg.hackathon.hackathon.common.response.ResponseCode;
import badukegg.hackathon.hackathon.member.service.ScoreService;
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
    public ApiResponseCustom<String> saveScores(@RequestBody List<Integer> scores) {
        if (scores.size() != 6) {
            return ApiResponseCustom.fail(ResponseCode.SCORE_ERROR);
        }

        scoreService.saveScores(scores);
        return ApiResponseCustom.success(ResponseCode.SCORE_SUCCESS);
    }

}
