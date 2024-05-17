package badukegg.hackathon.hackathon.member.service.impl;

import badukegg.hackathon.hackathon.common.exception.BaseException;
import badukegg.hackathon.hackathon.common.exception.UserException;
import badukegg.hackathon.hackathon.common.response.ResponseCode;
import badukegg.hackathon.hackathon.document.domain.Document;
import badukegg.hackathon.hackathon.member.domain.Member;
import badukegg.hackathon.hackathon.member.repository.MemberRepository;
import badukegg.hackathon.hackathon.member.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public boolean isMemberValid(String socialId) {
        Member member = memberRepository.findBySocialId(socialId).orElseThrow(() -> new UserException(ResponseCode.USER_NOT_FOUND));


        //authorizeMember(member);

        return member.getRScore() > 0 && member.getIScore() > 0 && member.getAScore() > 0 &&
                member.getSScore() > 0 && member.getEScore() > 0 && member.getCScore() > 0 &&
                member.getDocuments() != null && !member.getDocuments().isEmpty();
    }

//    private static void authorizeMember(Member member) {
//        String socialId = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        if(!member.getSocialId().equals(socialId)){
//            throw new BaseException(ResponseCode.FORBIDDEN);
//        }
//    }
}
