import React from 'react'
import  {Link} from 'react-router-dom'
import "./MatchDetailCard.scss"

const MatchDetailCard = ({teamName,match}) =>{
  if(!match) return null;
     const otherTeamName = match.homeTeam === teamName ? match.awayTeam:match.homeTeam;
     const otherRoute=`/team/${otherTeamName}`
     const t=teamName===match.homeTeam? "H":"A"
     let flag=""

     if(t=="H"){
       if(match.ftr==="H"){
         flag="green"
       }
       else if(match.ftr==="A"){
        flag="red"
       }
       else{
         flag="d"
       }
     }
     else{
      if(match.ftr==="A"){
        flag="green"
      }
      else if(match.ftr==="H"){
       flag="red"
      }
      else{
        flag="d"
      }
     }

 
  

    return (<div className= { flag ==='green' ? 'MatchDetailCard wonCard': flag==='red'? 'MatchDetailCard lostCard': 'MatchDetailCard drawCard'}>
                   {console.log(flag)}
           <div>
              
                  <h3>Latest Matches  </h3>
                    <span>vs</span>
                    <h1 className="name"> <Link  to={otherRoute}>
                            {otherTeamName} 
                        </Link>  
                    </h1>
             
              <div >
                  <h4 className="match-date">{match.date}</h4>
              
              
                 <h4 className="match-homeTeam">{match.homeTeam}  Stadium</h4>
              
        
                 <h4 className="match-result"> Result :  {match.homeTeam} {match.fthg} - {match.ftag}  {match.awayTeam} </h4>
              </div>
              
        </div>
       
    </div>)
}

export default MatchDetailCard