package com.springbootproject.laliga.LaLigaProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchWithDate {
    private long id;
    private String season;
    // date in LocalDate Type
    private LocalDate date;
    private String homeTeam;
    private String awayTeam;
    //Full-time home goals
    private int FTHG;
    //Full-time Away goals
    private int FTAG ;
    // Full-time result   (h:home team win, a:away team win, d:draw)
    private String FTR ;
}
