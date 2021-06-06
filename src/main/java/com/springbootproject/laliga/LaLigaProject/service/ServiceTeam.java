package com.springbootproject.laliga.LaLigaProject.service;


import com.springbootproject.laliga.LaLigaProject.model.Match;
import com.springbootproject.laliga.LaLigaProject.model.MatchWithDate;
import com.springbootproject.laliga.LaLigaProject.model.Team;
import com.springbootproject.laliga.LaLigaProject.repository.MatchRepository;
import com.springbootproject.laliga.LaLigaProject.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServiceTeam {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    MatchRepository matchRepository;

    public List<Team> getAllTeams(){
        return teamRepository.findAll();
    }


    /*
         findByTeam to get Team with a teamName
         This Team need also a list of Matches
         every Match has a date with different as String(10,0,8)  17/09/1995, 3/9/1995, 8/10/1995
         First convert every string date to nice Form of date --> dd/MM/yyyy
         Use LocalDate,DateTimeFormatter to convert a String date 17/09/95 to -> date 1995-09-17
         Because Match Class has an Atrribut date as String, define Class MatchWithDate with date as LocalDate
         Team has a list of MatchWithDate instead a list of Match
     */
    public Team findByTeamName(String teamName){
        // get first Team with a teamName
        Team team=teamRepository.findByTeamName(teamName);
        // get list of Matches of this teamName
        List<Match> matches=findLastetMatches(teamName);

       // Convert List of Match to List of  MatchWithDate
       List<MatchWithDate> matches1= matches.stream().map(match ->{
           MatchWithDate matchWithDate=new MatchWithDate();
           LocalDate localDate = null;
           DateTimeFormatter formatter = null;
            String date=match.getDate();
            String day="";
            String month="";
            String year="";
                    // 1:        17/09/1995 ---> 17/09/95
                    if(date.length()==10){

                        day=date.substring(0,2);
                        month=date.substring(3,5);
                        year=date.substring(6,10);
                        date=day+"/"+month+"/"+year;
                         match.setDate(date);
                        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        localDate = LocalDate.parse(date, formatter);
                        matchWithDate.setDate(localDate);

                    }
                    //  2:     3/9/1995 --> 03/09/95 , 27/02/16-->27/02/16
                       else if((date.length()==8)){
                        day=date.substring(0,date.indexOf('/')).length()==2 ?  date.substring(0,2) :  "0"+date.substring(0,1);
                       month= ( date.charAt(2)=='/' && date.charAt(5)=='/') ? date.substring(3,5) : "0"+date.substring(2,3);
                       year=  date.substring(6,8);
                        // To convert 95 to 1995
                        int yearInt= Integer.parseInt(year);
                        // add 1900 to yearInt if it is 95 to 99 (1999)
                        yearInt=yearInt>=95 && yearInt<=99 ? 1900+yearInt:2000+yearInt;
                        year=""+yearInt;
                       date=day+"/"+month+"/"+year;
                       match.setDate(date);
                        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        localDate = LocalDate.parse(date, formatter);
                        matchWithDate.setDate(localDate);

                      }
                         // 8/10/1995 --> 08/10/95 ||  10/9/1995--> 10/09/95 ||  10/1/1999
                       if(date.length()==9){
                           day=date.substring(0,date.indexOf('/')).length()==2 ?  date.substring(0,2) :  "0"+date.substring(0,1);
                           month=(date.charAt(1)=='/' && date.charAt(4)=='/')? date.substring(2,4) : "0"+date.substring(3,4);
                           year=date.substring(7,9);
                           // To convert 2095 to 1995
                           int yearInt= Integer.parseInt(year);
                           yearInt=yearInt>=95 && yearInt<=99 ? 1900+yearInt:2000+yearInt;
                           year=""+yearInt;
                           date=day+"/"+month+"/"+year;
                           match.setDate(date);
                           formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                           localDate = LocalDate.parse(date, formatter);

                           matchWithDate.setDate(localDate);
                       }
                       matchWithDate.setId(match.getId());
                       matchWithDate.setHomeTeam(match.getHomeTeam());
                       matchWithDate.setFTAG(match.getFTAG());
                       matchWithDate.setFTHG(match.getFTHG());
                       matchWithDate.setSeason(match.getSeason());
                       matchWithDate.setFTR(match.getFTR());
                       matchWithDate.setAwayTeam(match.getAwayTeam());

                    return matchWithDate;
        }).sorted(Comparator.comparing(MatchWithDate::getDate).reversed()).collect(Collectors.toList());

        // Get matchWins of this teamName using streams
        int matchWins= (int) matches1.stream().filter(match ->
                (match.getHomeTeam().equals(teamName) || match.getAwayTeam().equals(teamName)) && match.getFTR().equals("H")).
                count();

        if(team!=null){

                team.setMatches(matches1.subList(0,5));
               team.setTotalWins(matchWins);

        }

               return  team;
    }


    // get All Matches of this Team (teamName)
    public List<Match> findLastetMatches(String teamName){
        return  matchRepository.findByHomeTeamOrAwayTeam(teamName,teamName);
    }


    public List<MatchWithDate> getMatchesForTeamNameByYear(String teamName, int yearFromController){

        // get first Team with a teamName
        Team team=teamRepository.findByTeamName(teamName);
        // get list of Matches of this teamName
        List<Match> matches=findLastetMatches(teamName);

        // Convert List of Match to List of  MatchWithDate
        List<MatchWithDate> matches1= matches.stream().map(match ->{
            MatchWithDate matchWithDate=new MatchWithDate();
            LocalDate localDate = null;
            DateTimeFormatter formatter = null;
            String date=match.getDate();
            String day="";
            String month="";
            String year="";
            // 1:        17/09/1995 ---> 17/09/95
            if(date.length()==10){

                day=date.substring(0,2);
                month=date.substring(3,5);
                year=date.substring(6,10);
                date=day+"/"+month+"/"+year;
                match.setDate(date);
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                localDate = LocalDate.parse(date, formatter);
                matchWithDate.setDate(localDate);

            }
            //  2:     3/9/1995 --> 03/09/95 , 27/02/16-->27/02/16
            else if((date.length()==8)){
                day=date.substring(0,date.indexOf('/')).length()==2 ?  date.substring(0,2) :  "0"+date.substring(0,1);
                month= ( date.charAt(2)=='/' && date.charAt(5)=='/') ? date.substring(3,5) : "0"+date.substring(2,3);
                year=  date.substring(6,8);
                // To convert 95 to 1995
                int yearInt= Integer.parseInt(year);
                // add 1900 to yearInt if it is 95 to 99 (1999)
                yearInt=yearInt>=95 && yearInt<=99 ? 1900+yearInt:2000+yearInt;
                year=""+yearInt;
                date=day+"/"+month+"/"+year;
                match.setDate(date);
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                localDate = LocalDate.parse(date, formatter);
                matchWithDate.setDate(localDate);

            }
            // 8/10/1995 --> 08/10/95 ||  10/9/1995--> 10/09/95 ||  10/1/1999
            if(date.length()==9){
                day=date.substring(0,date.indexOf('/')).length()==2 ?  date.substring(0,2) :  "0"+date.substring(0,1);
                month=(date.charAt(1)=='/' && date.charAt(4)=='/')? date.substring(2,4) : "0"+date.substring(3,4);
                year=date.substring(7,9);
                // To convert 2095 to 1995
                int yearInt= Integer.parseInt(year);
                yearInt=yearInt>=95 && yearInt<=99 ? 1900+yearInt:2000+yearInt;
                year=""+yearInt;
                date=day+"/"+month+"/"+year;
                match.setDate(date);
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                localDate = LocalDate.parse(date, formatter);

                matchWithDate.setDate(localDate);
            }
            matchWithDate.setId(match.getId());
            matchWithDate.setHomeTeam(match.getHomeTeam());
            matchWithDate.setFTAG(match.getFTAG());
            matchWithDate.setFTHG(match.getFTHG());
            matchWithDate.setSeason(match.getSeason());
            matchWithDate.setFTR(match.getFTR());
            matchWithDate.setAwayTeam(match.getAwayTeam());

            return matchWithDate;
        }).sorted(Comparator.comparing(MatchWithDate::getDate).
                reversed()).
                filter(m->  m.getDate().getYear()==yearFromController).
                collect(Collectors.toList());


        return  matches1;
    }


    public List<MatchWithDate> getMatchesForTeamNameByYearAndMonth(String teamName, int yearFromController, String monthFromController){


        // get first Team with a teamName
        Team team=teamRepository.findByTeamName(teamName);
        // get list of Matches of this teamName
        List<Match> matches=findLastetMatches(teamName);

        // Convert List of Match to List of  MatchWithDate
        List<MatchWithDate> matches1= matches.stream().map(match ->{
            MatchWithDate matchWithDate=new MatchWithDate();
            LocalDate localDate = null;
            DateTimeFormatter formatter = null;
            String date=match.getDate();
            String day="";
            String month="";
            String year="";
            // 1:        17/09/1995 ---> 17/09/95
            if(date.length()==10){

                day=date.substring(0,2);
                month=date.substring(3,5);
                year=date.substring(6,10);
                date=day+"/"+month+"/"+year;
                match.setDate(date);
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                localDate = LocalDate.parse(date, formatter);
                matchWithDate.setDate(localDate);

            }
            //  2:     3/9/1995 --> 03/09/95 , 27/02/16-->27/02/16
            else if((date.length()==8)){
                day=date.substring(0,date.indexOf('/')).length()==2 ?  date.substring(0,2) :  "0"+date.substring(0,1);
                month= ( date.charAt(2)=='/' && date.charAt(5)=='/') ? date.substring(3,5) : "0"+date.substring(2,3);
                year=  date.substring(6,8);
                // To convert 95 to 1995
                int yearInt= Integer.parseInt(year);
                // add 1900 to yearInt if it is 95 to 99 (1999)
                yearInt=yearInt>=95 && yearInt<=99 ? 1900+yearInt:2000+yearInt;
                year=""+yearInt;
                date=day+"/"+month+"/"+year;
                match.setDate(date);
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                localDate = LocalDate.parse(date, formatter);
                matchWithDate.setDate(localDate);

            }
            // 8/10/1995 --> 08/10/95 ||  10/9/1995--> 10/09/95 ||  10/1/1999
            if(date.length()==9){
                day=date.substring(0,date.indexOf('/')).length()==2 ?  date.substring(0,2) :  "0"+date.substring(0,1);
                month=(date.charAt(1)=='/' && date.charAt(4)=='/')? date.substring(2,4) : "0"+date.substring(3,4);
                year=date.substring(7,9);
                // To convert 2095 to 1995
                int yearInt= Integer.parseInt(year);
                yearInt=yearInt>=95 && yearInt<=99 ? 1900+yearInt:2000+yearInt;
                year=""+yearInt;
                date=day+"/"+month+"/"+year;
                match.setDate(date);
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                localDate = LocalDate.parse(date, formatter);

                matchWithDate.setDate(localDate);
            }
            matchWithDate.setId(match.getId());
            matchWithDate.setHomeTeam(match.getHomeTeam());
            matchWithDate.setFTAG(match.getFTAG());
            matchWithDate.setFTHG(match.getFTHG());
            matchWithDate.setSeason(match.getSeason());
            matchWithDate.setFTR(match.getFTR());
            matchWithDate.setAwayTeam(match.getAwayTeam());

            return matchWithDate;
        }).sorted(Comparator.comparing(MatchWithDate::getDate).
                reversed()).
                filter(m->  m.getDate().getYear()==yearFromController && m.getDate().getMonth().equals(monthFromController)).
                collect(Collectors.toList());

        return  matches1;
    }

}
