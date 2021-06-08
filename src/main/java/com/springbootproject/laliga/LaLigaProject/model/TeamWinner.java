package com.springbootproject.laliga.LaLigaProject.model;

public class TeamWinner {
    private String season;
    private String winner;
    private String runner_up;

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getRunner_up() {
        return runner_up;
    }

    public void setRunner_up(String runner_up) {
        this.runner_up = runner_up;
    }

    public TeamWinner(String season, String winner, String runner_up) {
        this.season = season;
        this.winner = winner;
        this.runner_up = runner_up;
    }

    public  TeamWinner(){

    }
}
