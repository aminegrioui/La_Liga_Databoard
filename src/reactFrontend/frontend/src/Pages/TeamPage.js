import React from 'react'
import MatchDetailCard from '../Component/MatchDetailCard';
import MatchSmalCard from '../Component/MatchSmalCard';
import  {useParams,Link} from 'react-router-dom'
import { PieChart } from 'react-minimal-pie-chart';
import './TeamPage.scss'
const TeamPage = () =>{

    const [team,setTeam] = React.useState({matches : []})
    const {teamName}=useParams();
    let lastYear=2020;
    const fetchData =  async () =>{
        const reponse=await fetch(`http://localhost:8080/team/${teamName}`)
        const data=await reponse.json()
        console.log(data)
        setTeam(data)
    }
    React.useEffect(() =>{
        fetchData()
    }, [teamName])
     
    if(!team) return null;
    return <div className="TeamPage">

        <div className="teamName-section">
              <h1 className="teamName">{team.teamName}</h1>
        </div>

        <div className="win-loses-section">
            <h4>Wins/Loses/Draws</h4>
        <PieChart
            data={[
                { title: 'Loses', value: team.totalMatches - team.totalWins-team.totalDraws, color: '#a34d5d' },
                { title: 'Draws', value: team.totalDraws, color: '#302224' },
                { title: 'Wines', value: team.totalWins, color: '#4da375' }
            ]}
         />
        </div>
        
        <div className="match-detail-section">
            <MatchDetailCard  key={team.id} teamName={team.steamName}  match = {team.matches[0]} />
        </div>
        
       
       {
           
           team.matches.slice(1).map((m) => <MatchSmalCard key={m.id}  teamName={team.teamName} match = {m}/>)
       }
       <div className="more-link">
          <Link  to={`/team/${team.teamName}/matches/${lastYear}`}>
                        more >
                  </Link>
       </div>
       
      
        

    </div>
}

export default TeamPage