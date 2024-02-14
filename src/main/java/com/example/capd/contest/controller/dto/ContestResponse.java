package com.example.capd.contest.controller.dto;

public record ContestResponse(
        Long id,
        String title,
        String host,
        String targetParticipants,
        String receptionPeriod,
        String decisionPeriod,
        String compatitionArea,
        String award,
        String homepage,
        String howToApply,
        String fee,
        String image,
        String detailText
){}
