package com.nhnacademy.springboot.accountapi.repository;

import com.nhnacademy.springboot.accountapi.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
