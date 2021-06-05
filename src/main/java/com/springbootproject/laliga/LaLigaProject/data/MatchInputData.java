package com.springbootproject.laliga.LaLigaProject.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MatchInputData {


    private String season;
    private String date;
    private String homeTeam;
    private String awayTeam;
    //Full-time home goals
    private int FTHG;
    //Full-time Away goals
    private int FTAG;
    // Full-time result   (h:home team win, a:away team win, d:draw)
    private String FTR;

    private int HTHG;
    private int HTAG;
    private String HTR;
}
