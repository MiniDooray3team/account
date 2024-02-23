package com.nhnacademy.springboot.accountapi.repository;

import com.nhnacademy.springboot.accountapi.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberIdAndPassword(String memberId, String password);
}
