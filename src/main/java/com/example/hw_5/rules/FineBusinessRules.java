package com.example.hw_5.rules;


import com.example.hw_5.entity.Borrow;
import com.example.hw_5.entity.Fine;
import com.example.hw_5.entity.Member;
import com.example.hw_5.repository.MemberRepository;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Component
public class FineBusinessRules {
    private static final int DAILY_RATE =5 ; // günlük 5₺ ceza
    private static final int  PROCESS_FEE = 20; // işlem ücreti
    private final MemberRepository memberRepository;

    public FineBusinessRules(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 1. Gecikme cezası hesaplama
     */
    public int calculateLateFee(Date dueDate, Date returnDate) {
        if (dueDate == null || returnDate == null) {
            return 0;
        }

        long daysLate = ChronoUnit.DAYS.between(
                dueDate.toInstant(),
                returnDate.toInstant()
        );

        if (daysLate > 0) {
            return (int) (daysLate * DAILY_RATE);
        }

        return 0;
    }

    /**
     * 2. Üyenin ödenmemiş cezası var mı?
     */
    public boolean hasUnpaidFines(Member member) {
        List<Fine> fines = member.getFine();
        if (fines == null || fines.isEmpty()) {
            return false;
        }

        // Fine.status = false ise ödenmemiş kabul ediyoruz
        return fines.stream().anyMatch(fine -> !fine.isStatus());
    }

    /**
     * 3. Kayıp/hasar cezası hesaplama (kitap bedeli + işlem ücreti)
     */
    public int calculateLostOrDamagedFee(Borrow borrow) {
        if (borrow == null || borrow.getBook() == null) {
            return PROCESS_FEE; // en az işlem ücreti yansıtılır
        }

        double bookPrice = borrow.getBook().getPrice(); // Book entity’de price var kabul ettim
        return (int) (bookPrice + PROCESS_FEE);
    }

    //Is member exist ?
    public Member findMember(int memberId){
        return  memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }


}
