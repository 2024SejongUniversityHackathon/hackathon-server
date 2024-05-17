package badukegg.hackathon.hackathon.member.domain;

import jakarta.persistence.*;
import lombok.*;
import badukegg.hackathon.hackathon.common.domain.BaseTimeEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SocialProvider socialProvider;

    @Column(nullable = false)
    private String socialId;

    @Column(nullable = false)
    private String username;

    private String email;

    @Column(nullable = false)
    private LocalDate birthdate;

    public void setRole(Role role){
        this.role = role;
    }
    public void updateName(String username){
        this.username = username;
    }

    @Builder
    public Member(Role role, String username, SocialProvider socialProvider, String socialId, String email, LocalDate birthdate) {
        this.role = role;
        this.username = username;
        this.socialProvider = socialProvider;
        this.socialId = socialId;
        this.email = email;
        this.birthdate = birthdate;
    }

}
