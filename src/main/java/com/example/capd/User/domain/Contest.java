package com.example.capd.User.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity(name = "contest")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String title;

    @Column(length = 200)
    private String host;

    @Column(length = 200)
    private String targetParticipants;

    @Column(length = 40)
    private String receptionPeriod;

    @Column(length = 40)
    private String decisionPeriod;

    @Column(length = 200)
    private String compatitionArea;

    @Column(length = 200)
    private String award;
    @Column(length = 300)
    private String homepage;

    @Column(length = 20)
    private String howToApply;

    @Column(length = 20)
    private String fee;

    @Column(length = 400)
    private String image;

    @Column(length = 2000)
    private String detailText;
}
