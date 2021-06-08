import React from 'react'
import {Link} from 'react-router-dom'
import './YearSelector.scss'
const  YearSelector = ({teamName}) =>{

    let arrayYear=[]
    let start=1995;
    let end=2020;

    for(let i=start;i<=end;i++){
       arrayYear.push(i)
    }

    return (<div>
        
    <ol className="YearSelector">
    {
            arrayYear.map((year) =>{
                return <li>
                    <Link  to={`/team/${teamName}/matches/${year}`}>
                          {year}
                    </Link>
                    
                    
                    </li>
            })
        }
    </ol>
       

    </div>)
}
export default YearSelector