import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import './App.css';
import Header from './components/Header';
import Login from './components/Login';
import RegisterAccount from './components/RegisterAccount';
import AdminPanel from './components/AdminPanel';
import Homepage from './components/Homepage'; 
import Services from './components/Services'; 
import AddHaircut from './components/AddHaircut';  // Import the AddHaircut component
import EditHaircut from './components/EditHaircut'; // Import the EditHaircut component
import About from './components/About'; // Import the About component

function App() {
  return (
    <Router>
      <div className="App">
        <Header />
        <Routes>
          <Route path="/" element={<Navigate to="/homepage" />} />
          <Route path="/homepage" element={<Homepage />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<RegisterAccount />} />
          <Route path="/admin-panel" element={<AdminPanel />} />
          <Route path="/services" element={<Services />} />
          <Route path="/addHaircut" element={<AddHaircut />} /> {/* Add route for AddHaircut */}
          <Route path="/editHaircut/:id" element={<EditHaircut />} /> {/* Add route for EditHaircut */}
          <Route path="/about" element={<About />} /> {/* Add route for About */}
        </Routes>
      </div>
    </Router>
  );
}

export default App;



