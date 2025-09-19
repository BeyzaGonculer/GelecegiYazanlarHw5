package com.example.hw_5.service;

import com.example.hw_5.dto.member.request.CreateMemberRequest;
import com.example.hw_5.dto.member.response.CreateMemberResponse;
import com.example.hw_5.dto.member.response.GetMemberResponse;
import com.example.hw_5.entity.Fine;
import com.example.hw_5.entity.Member;
import com.example.hw_5.mapper.MemberMapper;
import com.example.hw_5.repository.MemberRepository;
import com.example.hw_5.rules.MemberBusinessRules;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final MemberBusinessRules memberBusinessRules;

    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper,MemberBusinessRules memberBusinessRules) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
        this.memberBusinessRules=memberBusinessRules;
    }

    public CreateMemberResponse createMember (@Valid CreateMemberRequest request){
        if (request.getStatus() == null || request.getStatus().isBlank()) {
            request.setStatus("ACTIVE");
        }
        memberBusinessRules.emailMustBeUnique(request.getEmail());
        memberBusinessRules.phoneAndEmailMustVerified(request.getPhoneNumber(), request.getEmail());

        Member member =memberMapper.createMemberRequestToMember(request);
        member = memberRepository.save(member); // DB’ye kaydet

        return memberMapper.memberToCreateMemberResponse(member);
    }

    public GetMemberResponse getMemberById(int id){

        Member member = memberRepository.findById(id) .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));
        return memberMapper.memberToGetMemberResponse(member);
    }

    public List<GetMemberResponse> getMembers (String status , String email){
        List<Member> members;
        if (status != null && email != null) {
            members = memberRepository.findByStatusAndEmail(status, email);
        } else if (status != null) {
            members = memberRepository.findByStatus(status);
        } else if (email != null) {
            members = memberRepository.findByEmail(email);
        } else {
            members = memberRepository.findAll();
        }

        List<GetMemberResponse> returnList = new ArrayList<>();
        for (Member member : members){
            returnList.add(memberMapper.memberToGetMemberResponse(member));
        }
        return returnList;
    }


    public GetMemberResponse updateStatus(int id, String status) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        member.setStatus(status);
        member = memberRepository.save(member);
        return memberMapper.memberToGetMemberResponse(member);
    }


    public List<GetMemberResponse>  getIsNotPaid(boolean isPaid){
        List<Member> allMember = new ArrayList<>();
        allMember = memberRepository.findByIsPaid(isPaid);
        List<GetMemberResponse> returnList = new ArrayList<>();
        for (Member member : allMember){
            returnList.add(memberMapper.memberToGetMemberResponse(member));
        }
        return returnList;
    }

}
