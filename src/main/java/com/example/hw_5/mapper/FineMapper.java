package com.example.hw_5.mapper;

import com.example.hw_5.dto.fine.request.CreateFineRequest;
import com.example.hw_5.dto.fine.response.CreateFineResponse;
import com.example.hw_5.dto.fine.response.GetFinesResponse;
import com.example.hw_5.entity.Fine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FineMapper {

    @Mapping(source = "member.memberId", target = "memberId")
    CreateFineResponse fineToCreateFineResponse(Fine fine);

    Fine createFineRequestToFine(CreateFineRequest request);

    @Mapping(source = "member.memberId", target = "memberId")
    GetFinesResponse fineToGetFinesResponse(Fine fine);
}