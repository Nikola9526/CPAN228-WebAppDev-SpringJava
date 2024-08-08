import React, { useEffect, useState } from 'react';
import './Services.css';

const Services = () => {
  const [haircuts, setHaircuts] = useState([]);

  useEffect(() => {
    const fetchHaircuts = async () => {
      try {
        const response = await fetch('http://localhost:8080/haircuts/api/allCuts');
        const data = await response.json();
        setHaircuts(data);
      } catch (error) {
        console.error('Error fetching haircut data:', error);
      }
    };

    fetchHaircuts();
  }, []);

  return (
    <div className="services-container">
      <h1 className="services-header">What we provide</h1>
      <table className="services-table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price ($)</th>
          </tr>
        </thead>
        <tbody>
          {haircuts.map((haircut) => (
            <tr key={haircut.id}>
              <td>{haircut.name}</td>
              <td>{haircut.description}</td>
              <td>{haircut.price}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Services;





