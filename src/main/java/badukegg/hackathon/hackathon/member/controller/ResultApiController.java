package badukegg.hackathon.hackathon.member.controller;

import badukegg.hackathon.hackathon.common.response.ApiResponseCustom;
import badukegg.hackathon.hackathon.common.response.ResponseCode;
import badukegg.hackathon.hackathon.member.service.ResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResultApiController {

    private final ResultService resultService;

    public ResultApiController(ResultService resultService) {
        this.resultService = resultService;
    }

    @Operation(summary = "생활기록부와 설문조사를 모두 완료했는지 검사", description = "생활기록부와 설문조사를 모두 완료했는지 검사하는 Controller")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "모두 성공"),
            @ApiResponse(responseCode = "500", description = "둘 중 하나를 하지 않았습니다.")
    })
    @GetMapping("/members/{memberId}/check")
    public ApiResponseCustom<?> checkAvailability(@PathVariable Long memberId) {
        boolean isAvailable = resultService.isMemberValid(memberId);
        if(isAvailable){
            return  ApiResponseCustom.success(isAvailable, ResponseCode.CHECK_SUCCESS);
        }
        return ApiResponseCustom.fail(ResponseCode.CHECK_ERROR);

    }
}
