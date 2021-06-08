package com.springbootproject.laliga.LaLigaProject;

import com.springbootproject.laliga.LaLigaProject.service.TeamWinnerService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class LaLigaProjectApplicationTests {
   @Autowired
	TeamWinnerService teamWinnerService;

   @Test
    public  void test() throws IOException {
	   Assert.assertEquals("2019-20",teamWinnerService.getTeamWinner("2019-20").getSeason());
	}

}
