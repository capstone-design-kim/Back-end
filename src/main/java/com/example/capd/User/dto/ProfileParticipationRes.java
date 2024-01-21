package com.example.capd.User.dto;

import com.example.capd.User.domain.Career;
import com.example.capd.User.domain.Participation;
import com.example.capd.User.domain.Profile;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ProfileParticipationRes {


    private Long id;//프로필 기본키
    private String userId;
    private String intro; //한줄 소개
    private double rate;
    private List<String> stackList; //스택
    private List<CareerParam> careers; //조회할때 경력도 한번에 넘겨주기 위함
    private String additional;
    private String time;



}
