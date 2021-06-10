import React from 'react'
import SeasonSelector from '../Component/SeasonSelector'
import TitelName from '../Component/TitelName'
import './HomePage.scss'
const HomePage = () =>{
  
    const [teams,setTeams] = React.useState( [])

    const fetchData =  async () =>{
        const reponse=await fetch(`http://localhost:8080/teams/`)
        const data=await reponse.json()
     
        setTeams(data)
    }
    React.useEffect(() =>{
        fetchData()
    }, [])
     
    return <div className="HomeTeam">
     
                    <div>
                        <h3>Who is the Champion in this Season: </h3>
                        <SeasonSelector />
                   </div>
         
             <div className="team-grid">
                    {
                        
                    
                    teams.map((team) => {
                        
                        return <TitelName  key={team.id} teamName={team.teamName}/> 
                    })
                }
                </div>
      
        </div>
        
    
      
        

    
}

export default HomePage