package com.nhnacademy.springboot.accountapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// @GeneratedValue(strategy = GenerationType.IDENTITY) // 이걸 추가하라고 함.
public class Member {

    @Id
    Long id;

    @Column(name = "member_id")
    String memberId;

    @Column(name = "password")
    String password;


    @Column(name = "email")
    String email;

    @Column(name = "member_status_id")
    int memberStatusId;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;

}
