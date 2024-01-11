package com.example.capd.User.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ProfileResponseDto {

    private Long id;
    private String intro; //한줄 소개
    private double rate;
    private List<String> stackList; //스택
    private List<CareerParam> careers; //조회할때 경력도 한번에 넘겨주기 위함
}