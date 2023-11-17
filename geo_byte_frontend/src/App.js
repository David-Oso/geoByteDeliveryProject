import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import SignUp from "./component/SignUp.jsx";
import Login from "./component/Login.jsx";
import Location from "./component/Location.jsx";
import LocationsTable from "./component/LocationsTable.jsx";
import VerifyEmail from "./component/VerifyEmail.jsx";
function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<SignUp/>}/>
        <Route path="/verify_email/:userId" element={<VerifyEmail/>}/>
        <Route path="/log-in" element={<Login/>}/>
        <Route path="/locations" element={<Location/>}/>
        <Route path="/locations-table" element={<LocationsTable/>}/>
      </Routes>
    </Router>
  )
}

export default App;
