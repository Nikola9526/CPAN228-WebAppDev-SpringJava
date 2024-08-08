import React from 'react';
import './About.css';

const About = () => {
  return (
    <div className="page-container">
      <div className="content-container">
        <h1 className="about-heading">About Us</h1>
        <h2 className="history-heading">Our History</h2>
        {/* filler text for about us page */}
        <p>
          Miami Cuts was founded in 2019 in the vibrant Miami metro area with a commitment to redefining the art of haircuts. 
          Our salon was created with the vision of providing a premier grooming experience, where each client’s unique style 
          is celebrated and enhanced by our expert stylists. Since opening our doors, we have strived to blend quality, creativity, 
          and personal attention to offer exceptional service and unmatched results. Join us at Miami Cuts, where every cut is 
          crafted with precision and passion.
        </p>
        {/* div for contact information, including google maps api key */}
        <div className="times-locations">
          <div className="text-content">
            <h3>Times & Locations</h3>
            <p>
              Toronto<br />
              4332 Yonge Street<br />
              Toronto, ON M2R5W4<br />
              647-234-1234<br />
              Monday-Friday: 10am-5pm<br />
              Saturday-Sunday: 9am-7pm
            </p>
          </div>
          <div className="map-container">
            <iframe
              src="https://www.google.com/maps/embed/v1/place?q=4332%20Yonge%20Street,%20Toronto,%20ON%20M2R5W4&key=AIzaSyDL_S6ZjH4UYtJFbsQOuPKicC3SmbZJTFg"
              allowFullScreen
              loading="lazy"
              title="Map Location"
            ></iframe>
          </div>
        </div>
      </div>
      <div className="background-layer"></div>
    </div>
  );
};

export default About;



