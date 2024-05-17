package badukegg.hackathon.hackathon.member.service.impl;

import badukegg.hackathon.hackathon.common.exception.BaseException;
import badukegg.hackathon.hackathon.common.exception.UserException;
import badukegg.hackathon.hackathon.common.response.ResponseCode;
import badukegg.hackathon.hackathon.member.domain.Member;
import badukegg.hackathon.hackathon.member.dto.request.ScoreRequest;
import badukegg.hackathon.hackathon.member.dto.response.ScoreResponse;
import badukegg.hackathon.hackathon.member.repository.MemberRepository;
import badukegg.hackathon.hackathon.member.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void saveScores(ScoreRequest scoresRequest, String socialId) {

        Member member = memberRepository.findBySocialId(socialId).orElseThrow(() -> new UserException(ResponseCode.USER_NOT_FOUND));
        List<Integer> scores = new ArrayList<>();

        scores.add(scoresRequest.getR());
        scores.add(scoresRequest.getI());
        scores.add(scoresRequest.getA());
        scores.add(scoresRequest.getS());
        scores.add(scoresRequest.getE());
        scores.add(scoresRequest.getC());

        member.setScore(scores);

        memberRepository.save(member);
    }
//    private static void authorizeMember(Member member) {
//        String socialId = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        if(!member.getSocialId().equals(socialId)){
//            throw new BaseException(ResponseCode.FORBIDDEN);
//        }
//    }

    @Override
    @Transactional
    public ScoreResponse getScores(String socialId) {
        Member member = memberRepository.findBySocialId(socialId).orElseThrow(() -> new UserException(ResponseCode.USER_NOT_FOUND));

        List<Integer> scores = member.getScore();

        return ScoreResponse.builder()
                .r(scores.get(0))
                .i(scores.get(1))
                .a(scores.get(2))
                .s(scores.get(3))
                .e(scores.get(4))
                .c(scores.get(5))
                .build();
    }

}
