package com.nhnacademy.springboot.accountapi.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@Setter
@DynamicInsert
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberId;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MemberStatus status;

    // atus Entity의 관계 부분
//    @ManyToOne
//    @JoinColumn(name = "status_id")
//    private MemberStatus statusEntity;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public MemberStatus getMemberStatus() {
        return status;
    }
}
