package com.springbootproject.laliga.LaLigaProject.data;

import com.springbootproject.laliga.LaLigaProject.model.Match;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class MatchDataProcessor implements ItemProcessor<MatchInputData, Match> {
    LocalDate localDate = null;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
    @Override
    public Match process(MatchInputData matchInputData) throws Exception {

        Match match=new Match();

        // Converting 'dd/MM/YY' String format to LocalDate
      //  localDate = LocalDate.parse(matchInputData.getDate(), formatter);
        match.setDate(matchInputData.getDate());
        match.setHomeTeam(matchInputData.getHomeTeam());
        match.setAwayTeam(matchInputData.getAwayTeam());

        match.setFTAG(matchInputData.getFTAG());
        match.setFTHG(matchInputData.getFTHG());
        match.setSeason(matchInputData.getSeason());

        match.setFTR(matchInputData.getFTR());

        return match;
    }
}
