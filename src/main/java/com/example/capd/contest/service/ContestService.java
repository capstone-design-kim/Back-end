package com.example.capd.contest.service;

import com.example.capd.User.domain.Contest;
import com.example.capd.contest.controller.dto.ContestResponse;
import com.example.capd.contest.repository.ContestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContestService {
    private final ContestRepository contestRepository;

    @Transactional(readOnly = true)
    public List<ContestResponse> findAllContest() {
        final List<Contest> contests = contestRepository.findAll();

        return contests.stream()
                .map(contest -> new ContestResponse(
                        contest.getId(),
                        contest.getTitle(),
                        contest.getHost(),
                        contest.getTargetParticipants(),
                        contest.getReceptionPeriod(),
                        contest.getDecisionPeriod(),
                        contest.getCompatitionArea(),
                        contest.getAward(),
                        contest.getHomepage(),
                        contest.getHowToApply(),
                        contest.getFee(),
                        contest.getImage(),
                        contest.getDetailText())).toList();
    }
}
