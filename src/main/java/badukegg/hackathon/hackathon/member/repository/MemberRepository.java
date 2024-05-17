package badukegg.hackathon.hackathon.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import badukegg.hackathon.hackathon.member.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findBySocialId(String socialId);
    Optional<Member> findByEmail(String email);
}
