package badukegg.hackathon.hackathon.document.domain;

import badukegg.hackathon.hackathon.common.domain.BaseTimeEntity;
import badukegg.hackathon.hackathon.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Document extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    public void setMember(Member member){
        this.member = member;
    }
    @Builder
    public Document (String fileName, String filePath, Member member) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.member = member;
    }
}
