package com.springbootproject.laliga.LaLigaProject.repository;

import com.springbootproject.laliga.LaLigaProject.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match,Long> {

    List<Match> findByHomeTeamOrAwayTeam(String homeTeam, String awayTeam );
}
