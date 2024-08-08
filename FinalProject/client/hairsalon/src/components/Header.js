import React from 'react';
import { Link } from 'react-router-dom';
import './Header.css';

const Header = () => {
  return (
    <nav>
      <ul id="navbar">
        <img src="/Blogo.PNG" alt="logo1" width="390px" />
        <div>
          <li className="nav"><Link to="/homepage" id="REF">HomePage</Link></li>
          <li className="nav"><Link to="/about">About Us</Link></li>
          <li className="nav"><Link to="/services">Services</Link></li>
          <li className="nav"><Link to="/login">Login</Link></li>
        </div>
      </ul>
    </nav>
  );
};

export default Header;




