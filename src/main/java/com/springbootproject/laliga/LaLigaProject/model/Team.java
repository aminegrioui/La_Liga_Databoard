package com.springbootproject.laliga.LaLigaProject.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String teamName;

    private long totalMatches;
    private long totalWins;
    private long totalDraws;

    @Transient
    // A list of Matches but with converted date date(String) --> date(LocalDates)
    private List<MatchWithDate> matches=new ArrayList<>();

    public Team(String teamName ,long totalMatches) {
        this.teamName = teamName;
        this.totalMatches = totalMatches;
    }




    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", totalMatches=" + totalMatches +
                ", totalWins=" + totalWins +
                '}';
    }
}
