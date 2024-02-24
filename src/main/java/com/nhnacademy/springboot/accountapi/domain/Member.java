package com.nhnacademy.springboot.accountapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    Long id;

    @Column(name = "member_id")
    String memberId;

    @Column(name = "password")
    String password;


    @Column(name = "email")
    String email;

    @ManyToOne
    @JoinColumn(name = "member_status_id")
    private MemberStatus memberStatus; // MemberSt

    // atus Entity의 관계 부분

    @Column(name = "created_at")
    LocalDateTime createdAt;

}
