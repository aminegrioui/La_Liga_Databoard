package com.springbootproject.laliga.LaLigaProject.data;

import com.springbootproject.laliga.LaLigaProject.model.Match;
import com.springbootproject.laliga.LaLigaProject.model.Team;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;


@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {



    private final EntityManager entityManager;

    @Autowired
    public JobCompletionNotificationListener(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        Map<String, Team> stringTeamMap=new HashMap<>();
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
        /*    jdbcTemplate.query("SELECT home_team, away_team,FTR, season FROM Match",
                    (rs, row) -> new Match(
                            rs.getString(1),
                            rs.getString(2),
                           rs.getString(3),
                            rs.getString(4))
            ).forEach(match -> System.out.println(match));*/

            entityManager.createQuery("select new  com.springbootproject.laliga.LaLigaProject.model.Team ( m.homeTeam,count(*)) from Match m group by m.homeTeam", Team.class).
                    getResultList().
                    forEach(team -> stringTeamMap.put(team.getTeamName(), team));

     //   stringTeamMap.values().stream().forEach(System.out::println);
            System.out.println(" after last result \n");
            /*
              other statement to target teamName, cause teamName can passed in team1 or team2
             */
           entityManager.createQuery("select  new  com.springbootproject.laliga.LaLigaProject.model.Team ( m.awayTeam, count(*)) from Match m group by m.awayTeam", Team.class).
                    getResultList().
                    forEach(team->{
                        // get TotalMatches of this team from the previous map
                        long l=stringTeamMap.get(team.getTeamName()).getTotalMatches();
                        team.setTotalMatches(team.getTotalMatches() + l);
                        stringTeamMap.put(team.getTeamName(),team);
                        //  System.out.println(team);
                    });
           // stringTeamMap.values().stream().forEach(System.out::println);



         stringTeamMap.values().forEach(team -> entityManager.persist(team));



        }
    }
}
