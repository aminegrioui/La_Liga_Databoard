package com.springbootproject.laliga.LaLigaProject.service;


import com.springbootproject.laliga.LaLigaProject.model.TeamWinner;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.reader.StreamReader;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class TeamWinnerService {
    File file=new File("C:\\Users\\mog\\Desktop\\Trinee\\Coole Great Projecten\\LaLiga-Project\\LaLiga-Project\\src\\main\\resources\\dataOfWinners.txt");
    private Map<String, TeamWinner> stringTeamWinnerMap=new HashMap<>();

    public TeamWinner getTeamWinner(String season) throws IOException {
        String[] words=null;
         TeamWinner teamWinner=new TeamWinner();
        BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
        String line=bufferedReader.readLine();
        while (line!=null){
             words=line.split(",");


            teamWinner=new TeamWinner(words[0],words[1],words[2]);


            line=bufferedReader.readLine();
            stringTeamWinnerMap.put(teamWinner.getSeason(), teamWinner);
        }
        return stringTeamWinnerMap.get(season);

    }
}
