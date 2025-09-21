package com.example.hw_5.repository;

import com.example.hw_5.entity.Fine;
import com.example.hw_5.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FineRepository extends JpaRepository<Fine,Integer> {
    List<Fine> findByMemberMemberId(int memberId);

}
