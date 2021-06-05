package com.springbootproject.laliga.LaLigaProject.repository;

import com.springbootproject.laliga.LaLigaProject.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {
    Team findByTeamName(String teamName);
}
