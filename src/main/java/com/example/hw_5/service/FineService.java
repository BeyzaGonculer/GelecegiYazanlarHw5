package com.example.hw_5.service;

import com.example.hw_5.dto.fine.request.CreateFineRequest;
import com.example.hw_5.dto.fine.response.CreateFineResponse;
import com.example.hw_5.dto.fine.response.GetFinesResponse;
import com.example.hw_5.entity.Fine;
import com.example.hw_5.entity.Member;
import com.example.hw_5.mapper.FineMapper;
import com.example.hw_5.repository.FineRepository;
import com.example.hw_5.repository.MemberRepository;
import com.example.hw_5.rules.FineBusinessRules;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class FineService {

    private final FineRepository fineRepository;
    private final FineMapper fineMapper;
    private final FineBusinessRules fineBusinessRules;

    public FineService(FineRepository fineRepository, FineMapper fineMapper, FineBusinessRules fineBusinessRules
) {
        this.fineRepository = fineRepository;
        this.fineMapper = fineMapper;
        this.fineBusinessRules=fineBusinessRules;
    }

    public CreateFineResponse createFine(int memberId, CreateFineRequest request) {
        Member member = fineBusinessRules.findMember(memberId);

        Fine fine = fineMapper.createFineRequestToFine(request);
        fine.setMember(member);

        fineRepository.save(fine);

        return fineMapper.fineToCreateFineResponse(fine);
    }

    public List<GetFinesResponse> getFines (int memberId){
        List<Fine> fines = fineRepository.findByMemberMemberId(memberId);
         return fines.stream()
                .map(fineMapper::fineToGetFinesResponse)  // her bir Fine -> GetFinesResponse
                .collect(Collectors.toList());
    }
}
