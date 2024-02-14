package com.example.capd.contest.service;

import com.example.capd.User.domain.Contest;
import com.example.capd.contest.repository.ContestRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CrawlingScriptService {

    private final ContestRepository contestRepository;
    private final List<String> filePath =
            List.of("contestkorea__contest_data.json",
                    "wevity_contest_data.json",
                    "thinkgood_contest_data.json");

    @Scheduled(fixedRate = 30000)
    public void executeCrawlingScript() {
        try {
            String pythonScriptPath = "C:/IntelliJ/crawlingpracticeCopy/src/main/java/crawlingpractice/example/crawlingpractice/contestkorea_crawling.py";

            ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            int exitCode = process.waitFor();
            System.out.println("파이썬 스크립트 실행 종료, 종료 코드: " + exitCode);

            filePath.forEach(this::readContestkorea);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void readContestkorea(final String siteName) {
        try (FileReader reader = new FileReader(siteName)) {
            JSONParser parser = new JSONParser(reader);
            JSONArray jsonArray = (JSONArray) parser.parse();

            for (Object json : jsonArray) {
                JSONObject jsonObj = (JSONObject) json;
                String contestTitle = (String) jsonObj.get(siteName);

                Contest existingContest = contestRepository.findByTitle(contestTitle);
                if (existingContest != null) {
                    continue;
                }

                final Contest contest = Contest.builder()
                        .title((String) jsonObj.get("contest_title"))
                        .host((String) jsonObj.get("contest_host"))
                        .targetParticipants((String) jsonObj.get("contest_target_participants"))
                        .receptionPeriod((String) jsonObj.get("contest_reception_period"))
                        .decisionPeriod((String) jsonObj.get("contest_decision_period"))
                        .compatitionArea((String) jsonObj.get("contest_compatition_area"))
                        .award((String) jsonObj.get("contest_award"))
                        .homepage((String) jsonObj.get("contest_homepage"))
                        .howToApply((String) jsonObj.get("contest_how_to_apply"))
                        .fee((String) jsonObj.get("contest_fee"))
                        .image((String) jsonObj.get("contest_image"))
                        .detailText((String) jsonObj.get("contest_detail_text"))
                        .build();

                contestRepository.save(contest);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}