package badukegg.hackathon.hackathon.member.service;

import badukegg.hackathon.hackathon.member.dto.request.MemberRequestDto;
import badukegg.hackathon.hackathon.member.dto.request.ScoreRequest;
import badukegg.hackathon.hackathon.member.dto.response.MemberResponseDto;

import java.util.List;

public interface ScoreService {

    void saveScores(ScoreRequest scoresRequest, String socialId);
}
