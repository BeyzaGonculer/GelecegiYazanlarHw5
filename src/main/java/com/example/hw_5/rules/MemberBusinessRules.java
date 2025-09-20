package com.example.hw_5.rules;

import com.example.hw_5.core.exception.MemberBusinessException;
import com.example.hw_5.repository.MemberRepository;
import org.springframework.stereotype.Component;

@Component
public class MemberBusinessRules {

    private final MemberRepository memberRepository;

    public MemberBusinessRules(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // 1.Email benzersiz olmalı.
    public void emailMustBeUnique(String email){
        if (memberRepository.existsByEmail(email)) {
            throw new MemberBusinessException("This email is already registered!");
        }
    }

    // 2. membershipLevel=STANDARD üyelerin aynı anda en fazla 3 açık ödünç kaydı olabilir;
    // GOLD için 5.
    public void memberHaveLimitedBarrows(String memberShipLevel , int activeBorrowCount ){
        if (memberShipLevel.equalsIgnoreCase("STANDARD") && activeBorrowCount >= 3) {
            throw new IllegalStateException("STANDARD members can have at most 3 active borrows!");
        }

        if (memberShipLevel.equalsIgnoreCase("GOLD") && activeBorrowCount >= 5) {
            throw new IllegalStateException("GOLD members can have at most 5 active borrows!");
        }
    }

    // 3. BANNED üyeler ödünç/rezervasyon yapamaz.
    public void bannedMembersCannotBarrow(boolean isBanned){
        if (isBanned) {
            throw new MemberBusinessException("BANNED members cannot borrow or reserve books!");
        }
    }

    // 4. Telefon/email formatları doğrulanmalı.
    public void phoneAndEmailMustVerified(String phoneNumber, String email){
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new MemberBusinessException("Invalid email format!");
        }

        if (!phoneNumber.matches("^[0-9]{10,15}$")) {
            throw new MemberBusinessException("Phone number must be 10-15 digits!");
        }
    }
}
