package com.example.hw_5.repository;

import com.example.hw_5.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    boolean existsByEmail(String email);

    List<Member> findByStatus(String status);
    List<Member> findByEmail(String email);
    List<Member> findByStatusAndEmail(String status, String email);
    List<Member> findByIsPaid(boolean isPaid);
}
