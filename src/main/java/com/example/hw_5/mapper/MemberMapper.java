package com.example.hw_5.mapper;

import com.example.hw_5.dto.member.request.CreateMemberRequest;
import com.example.hw_5.dto.member.response.CreateMemberResponse;
import com.example.hw_5.dto.member.response.GetMemberResponse;
import com.example.hw_5.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    @Mapping(target = "status", defaultValue = "ACTIVE")
    Member createMemberRequestToMember(CreateMemberRequest request);

    CreateMemberResponse memberToCreateMemberResponse(Member member);

    GetMemberResponse memberToGetMemberResponse(Member member);
}