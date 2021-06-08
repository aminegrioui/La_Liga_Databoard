package com.springbootproject.laliga.LaLigaProject.controller;

import com.springbootproject.laliga.LaLigaProject.model.TeamWinner;
import com.springbootproject.laliga.LaLigaProject.service.TeamWinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TeamWinnerController {
    @Autowired
    private TeamWinnerService teamWinnerService;
    @GetMapping("/teamWinner")
    public TeamWinner getTeamWinnerOfSeason( @RequestParam String season)  throws IOException {
        return  teamWinnerService.getTeamWinner(season);
    }
}
