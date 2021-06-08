import React from 'react'
import  {Link} from 'react-router-dom'
const MatchSmalCard = ({teamName,match}) =>{
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
 
    return (<div  className= { flag ==='green' ? 'MatchSmalCard wonCard': flag==='red'? 'MatchSmalCard lostCard': 'MatchSmalCard drawCard'}>
              <span>vs</span>

          <p> <Link  to={otherRoute}>
                      {otherTeamName} 
                  </Link> </p>
    </div>)
}

export default MatchSmalCard