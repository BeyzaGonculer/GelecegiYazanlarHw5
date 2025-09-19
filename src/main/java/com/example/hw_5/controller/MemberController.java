package com.example.hw_5.controller;

import com.example.hw_5.dto.member.request.CreateMemberRequest;
import com.example.hw_5.dto.member.response.CreateMemberResponse;
import com.example.hw_5.dto.member.response.GetMemberResponse;
import com.example.hw_5.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateMemberResponse createMember(@Valid @RequestBody CreateMemberRequest request) {

        return memberService.createMember(request);
    }

    @GetMapping("{id}")
    public GetMemberResponse getMemberById(@PathVariable int id) {

        return memberService.getMemberById(id);
    }


    @GetMapping
    public List<GetMemberResponse> getMembers(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String email) {

        List<GetMemberResponse> members = memberService.getMembers(status, email);
        return members;
    }

    @PatchMapping("/{id}/status")
    public GetMemberResponse updateMemberStatus(
            @PathVariable int id,
            @RequestParam String value) {
        return memberService.updateStatus(id, value);
    }

    @GetMapping("/fines")
    public List<GetMemberResponse> getIsNotPaid(@RequestParam boolean isPaid) {
        return memberService.getIsNotPaid(isPaid);
    }


}
