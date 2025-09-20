package com.example.hw_5.rules;

import com.example.hw_5.core.exception.BorrowBusinessException;
import com.example.hw_5.entity.Member;
import com.example.hw_5.repository.MemberRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class BorrowBusinessRules {

    private final MemberRepository memberRepository;

    public BorrowBusinessRules(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member findMemberById(int memberId){
        Member member = memberRepository.findById(memberId).stream().findFirst().orElseThrow(() -> new RuntimeException("Member not found"));
        return member;
    }
}
