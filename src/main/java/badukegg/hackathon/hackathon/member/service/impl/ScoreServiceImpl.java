package badukegg.hackathon.hackathon.member.service.impl;

import badukegg.hackathon.hackathon.common.exception.BaseException;
import badukegg.hackathon.hackathon.common.exception.UserException;
import badukegg.hackathon.hackathon.common.response.ResponseCode;
import badukegg.hackathon.hackathon.member.domain.Member;
import badukegg.hackathon.hackathon.member.repository.MemberRepository;
import badukegg.hackathon.hackathon.member.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void saveScores(List<Integer> scores) {

        Member member = memberRepository.findById(1L).orElseThrow(() -> new UserException(ResponseCode.USER_NOT_FOUND));
        authorizeMember(member);
        member.setScore(scores);

        memberRepository.save(member);
    }
    private static void authorizeMember(Member member) {
        String socialId = SecurityContextHolder.getContext().getAuthentication().getName();

        if(!member.getSocialId().equals(socialId)){
            throw new BaseException(ResponseCode.FORBIDDEN);
        }
    }
}
