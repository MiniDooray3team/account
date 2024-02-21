package com.nhnacademy.springboot.accountapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
public class Member {

    @Id
    Long id;

    @Column(name = "member_id")
    String memeberId;

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
