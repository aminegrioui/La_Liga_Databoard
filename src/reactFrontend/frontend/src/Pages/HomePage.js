import React from 'react'
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
         <div className="header-section">
              <h1 className="app-name">  Java Brains IPL Databoard </h1>
        </div>
        
        <div className="team-grid">
        {
           
           teams.map((team) => {
               console.log(team.teamName)
               return <TitelName  key={team.id} teamName={team.teamName}/> 
           })
       }
        </div>
      
      
        

    </div>
}

export default HomePage