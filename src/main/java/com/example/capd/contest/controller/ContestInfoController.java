package com.example.capd.contest.controller;

import com.example.capd.contest.domain.Contest;
import com.example.capd.contest.service.ContestInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ContestInfoController {

    @Autowired
    private final ContestInfoService contestInfoService;

    @GetMapping("/showData")
    public String showData(Model model) {
        List<Map<String, Object>> data = contestInfoService.fetchDataFromDatabase();
        model.addAttribute("data", data);
        return "showDataPage";
    }

    @GetMapping("/contestData")
    public List<Contest> contestData() {
        return contestInfoService.getAllContests();
    }

    //공모전 id 리스트
    @GetMapping("/contest-list")
    public List<Long> contestIdList(){
        return contestInfoService.getContestId();
    }

    @GetMapping("/contestdetail")
    public ResponseEntity<Contest> getContestDetail(@RequestParam Long id) {
        Contest contest = contestInfoService.findContestDetailById(id);
        if(contest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(contest, HttpStatus.OK);
        }
    }

    @PostMapping("/viewPlus")
    public ResponseEntity<String> increaseViewCount(@RequestBody Map<String, Long> viewsIncreaseRequest) {
        Long contestId = viewsIncreaseRequest.get("contestId");
        Long addedViews = viewsIncreaseRequest.get("addedViews");

        Contest contest = contestInfoService.findContestDetailById(contestId);
        if (contest == null) {
            return new ResponseEntity<>("Contest not found", HttpStatus.NOT_FOUND);
        } else {
            contest.setViews(addedViews);
            contestInfoService.saveContest(contest);
            return new ResponseEntity<>("View count increased successfully", HttpStatus.OK);
        }
    }
}