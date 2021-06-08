import React from 'react'
import  {Link} from 'react-router-dom'
import './TitelName.scss'
const TitelName = ({teamName}) =>{
     console.log(teamName)
     return <div className="TitelName">
             <h1><Link to={`/team/${teamName}`}>
                   {teamName}
                </Link>
                </h1>
         </div>


}
export default TitelName;