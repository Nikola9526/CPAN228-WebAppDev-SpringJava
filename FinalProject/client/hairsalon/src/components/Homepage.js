import React from 'react';
import { Link } from 'react-router-dom'; // Import Link from react-router-dom
import './Homepage.css';

const Homepage = () => {
  return (
    <div className="homepage">
      <div className="pink-rectangle">
        <h1 className="large-heading">Hair is Our Craft</h1>
        {/* temporary filler text for homepage */}
        <p>
          At Miami Cuts, we believe that a great haircut is more than just a routine—it’s an essential part of self-expression. Located in the vibrant city of Miami, our salon is a sanctuary for those who value style, quality, and a personal touch. Whether you're preparing for a special occasion, looking to reinvent your look, or simply in need of a refresh, our team of highly skilled stylists is here to provide an exceptional experience tailored just for you.
        </p>
        <div className="button-container">
          <Link to="/about">
            <button className="learn-more-button">Learn More</button>
          </Link>
          <Link to="/services">
            <button className="services-button">Our Services</button>
          </Link>
        </div>
      </div>
      <div className="image-container">
        <img src="/haircut4.png" alt="Haircut" className="background-image" />
      </div>
    </div>
  );
};

export default Homepage;



