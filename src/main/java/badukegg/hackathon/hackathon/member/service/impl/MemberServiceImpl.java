package badukegg.hackathon.hackathon.member.service.impl;

import badukegg.hackathon.hackathon.common.exception.UserException;
import badukegg.hackathon.hackathon.common.response.ResponseCode;
import badukegg.hackathon.hackathon.member.domain.Member;
import badukegg.hackathon.hackathon.member.domain.Role;
import badukegg.hackathon.hackathon.member.domain.SocialProvider;
import badukegg.hackathon.hackathon.member.dto.request.MemberRequestDto;
import badukegg.hackathon.hackathon.member.dto.response.MemberResponseDto;
import badukegg.hackathon.hackathon.member.repository.MemberRepository;
import badukegg.hackathon.hackathon.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public MemberResponseDto findByAppleId(String appleId){
        Member member = memberRepository.findBySocialId(appleId).orElseThrow();
        if(member !=  null){
            return MemberResponseDto.builder()
                    .email(member.getEmail()).build();
        }
        else {
            throw new UserException(ResponseCode.USER_NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public MemberResponseDto saveMember(MemberRequestDto memberRequestDto){
        Member findMember = memberRepository.findByEmail(memberRequestDto.getEmail()).orElse(null);
        if (findMember == null) {
            Member member = Member.builder()
                    .email(memberRequestDto.getEmail())
                    .username(memberRequestDto.getUsername())
                    .role(Role.USER)
                    .socialProvider(SocialProvider.APPLE)
                    .socialId(memberRequestDto.getSocialId())
                    .build();
            memberRepository.save(member);
            return MemberResponseDto.builder()
                    .email(member.getEmail()).build();
        }
        else {
            return MemberResponseDto.builder()
                    .email(findMember.getEmail()).build();
        }

    }
}
