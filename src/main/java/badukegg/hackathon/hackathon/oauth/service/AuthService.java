package badukegg.hackathon.hackathon.oauth.service;

import badukegg.hackathon.hackathon.common.exception.UserException;
import badukegg.hackathon.hackathon.common.response.ResponseCode;
import badukegg.hackathon.hackathon.member.domain.Member;
import badukegg.hackathon.hackathon.member.domain.Role;
import badukegg.hackathon.hackathon.member.domain.SocialProvider;
import badukegg.hackathon.hackathon.member.dto.request.MemberRequestDto;
import badukegg.hackathon.hackathon.member.repository.MemberRepository;
import badukegg.hackathon.hackathon.oauth.dto.TokenDto;
import badukegg.hackathon.hackathon.oauth.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public TokenDto login(MemberRequestDto memberRequestDto){
        Member member = memberRepository.findBySocialId(memberRequestDto.getSocialId()).orElse(null);

        if(member == null){
            member = Member.builder()
                    .socialId(memberRequestDto.getSocialId())
                    .username(memberRequestDto.getUsername())
                    .email(memberRequestDto.getEmail())
                    .socialProvider(SocialProvider.APPLE)
                    .role(Role.USER)
                    .build();
            memberRepository.save(member);
        }

        TokenDto tokenDto = jwtProvider.generateTokenDto(memberRequestDto.getSocialId());

        return tokenDto;
    }


//    public void logout(String accessToken) {
//
//    }
}
