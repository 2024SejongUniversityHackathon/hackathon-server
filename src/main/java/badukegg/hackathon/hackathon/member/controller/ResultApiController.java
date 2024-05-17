package badukegg.hackathon.hackathon.member.controller;

import badukegg.hackathon.hackathon.common.response.ApiResponseCustom;
import badukegg.hackathon.hackathon.common.response.ResponseCode;
import badukegg.hackathon.hackathon.member.service.ResultService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResultApiController {

    private final ResultService resultService;

    public ResultApiController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping("/members/{memberId}/check")
    public ApiResponseCustom<?> checkAvailability(@PathVariable Long memberId) {
        boolean isAvailable = resultService.isMemberValid(memberId);
        if(isAvailable){
            return  ApiResponseCustom.success(isAvailable, ResponseCode.CHECK_SUCCESS);
        }
        return ApiResponseCustom.fail(ResponseCode.CHECK_ERROR);

    }
}
