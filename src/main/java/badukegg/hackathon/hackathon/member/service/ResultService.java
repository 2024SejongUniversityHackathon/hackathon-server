package badukegg.hackathon.hackathon.member.service;

import badukegg.hackathon.hackathon.member.dto.request.MemberRequestDto;
import badukegg.hackathon.hackathon.member.dto.response.MemberResponseDto;

public interface ResultService {
    boolean isMemberValid(String socialId);
}
