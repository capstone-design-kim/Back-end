package com.example.capd.contest.controller;

import com.example.capd.contest.controller.dto.ContestResponse;
import com.example.capd.contest.service.ContestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contests")
public class ContestController {
    private final ContestService contestService;

    @GetMapping
    public ResponseEntity<List<ContestResponse>> findAllContest() {
        final List<ContestResponse> contestResponses = contestService.findAllContest();
        return ResponseEntity.ok(contestResponses);
    }
}
