package com.springbootproject.laliga.LaLigaProject.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@Getter
@Setter
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String season;
    private String date;
    private String homeTeam;
    private String awayTeam;
    //Full-time home goals
    private int FTHG;
    //Full-time Away goals
    private int FTAG ;
    // Full-time result   (h:home team win, a:away team win, d:draw)
    private String FTR ;

    public Match(){

    }
    public Match(String  homeTeam,String  awayTeam, String result,String season){
        this.homeTeam=homeTeam;
        this.awayTeam=awayTeam;
        this.season=season;
        this.FTR=result;
    }


    @Override
    public String toString() {
        return "Match{" +
                "season='" + season + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", awayTeam='" + FTR + '\'' +
                '}';
    }
}
