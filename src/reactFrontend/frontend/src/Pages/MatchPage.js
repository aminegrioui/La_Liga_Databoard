import React from 'react'
import MatchDetailCard from '../Component/MatchDetailCard';
import YearSelector from '../Component/YearSelector';
import  {useParams,Link} from 'react-router-dom'
import './MatchPage.scss'
const MatchPage = () =>{

    const [matches,setMatches] = React.useState([])
    const {teamName,year}=useParams();
    const fetchData =  async () =>{
        const reponse=await fetch(`http://localhost:8080/team/${teamName}/matches?year=${year}`)
        const data=await reponse.json()
      
        setMatches(data)
    }
    React.useEffect(() =>{
        fetchData()
    }, [teamName,year])
     
    
    return <div className="MatchPage">
     
       <div className="year-selector-section">
            <h3>Select Year </h3>
            <YearSelector teamName={teamName} />
           
        </div>
       
    <div >
    <h1 className="page-hiding">{teamName} Matches in {year}</h1>
    {
             matches.map((match) =>{
               return  <MatchDetailCard  key={match.id} teamName={teamName}  match = {match} />
             })
         }
    </div>
        
         

    </div>
}

export default MatchPage