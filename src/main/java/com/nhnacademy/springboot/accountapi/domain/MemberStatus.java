package com.nhnacademy.springboot.accountapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member_status")
public class MemberStatus {

    @Id
    int id;

    @Column(name = "name")
    String name;
}
