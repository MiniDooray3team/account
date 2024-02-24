package com.nhnacademy.springboot.accountapi.domain;

import javax.persistence.*;

@Entity
@Table(name = "member_status")
public class MemberStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name")
    String name;

    public int getStatusId() {
        return id;
    }

    public void setStatusId(int statusId) {
        this.id = statusId;
    }
}
