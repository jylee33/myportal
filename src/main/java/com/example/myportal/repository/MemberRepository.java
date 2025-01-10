package com.example.myportal.repository;

import com.example.myportal.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findById(Long id);

    Optional<Member> findByName(String name);
    List<Member> findAll();
}
