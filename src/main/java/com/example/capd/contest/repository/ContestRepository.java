package com.example.capd.contest.repository;

import com.example.capd.User.domain.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Long> {

    @Query(value = "select * from capstone_crawling.contest", nativeQuery = true)
    List<Map<String, Object>> fetchDataFromDatabase();

    Contest findByTitle(String title);
}