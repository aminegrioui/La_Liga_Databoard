import './App.scss';
import TeamPage from './Pages/TeamPage';
import { BrowserRouter  as Router, Route , Switch} from "react-router-dom";
import MatchPage from './Pages/MatchPage';
import HomePage from './Pages/HomePage';
import TeamWinnerPage from './Pages/TeamWinnerPage'
function App() {
  return (
    <div className="App">
       <Router>
        <Switch>
                   <Route  path="/team/:teamName/matches/:year">
                        <MatchPage/>
                  </Route>
                   <Route  path="/team/:teamName">
                        <TeamPage/>
                  </Route>
                  <Route  path="/teamWinner/:yearSeason">
                        <TeamWinnerPage/>
                  </Route>
                  <Route  path="/">
                        <HomePage/>
                  </Route>

        </Switch>
           
      </Router>
     

        
    </div>
  );
}

export default App;
