import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './AddHaircut.css';

const AddHaircut = () => {
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [price, setPrice] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    const newHaircut = {
      name,
      description,
      price: parseFloat(price),
    };

    try {
      const response = await fetch('http://localhost:8080/haircuts/api/allCuts', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newHaircut),
      });

      if (response.ok) {
        navigate('/admin-panel');
      } else {
        console.error('Failed to add haircut');
      }
    } catch (error) {
      console.error('Error adding haircut:', error);
    }
  };

  return (
    <div className="add-haircut">
      <h2>Add Haircut</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="name">Name:</label>
          <input type="text" id="name" value={name} onChange={(e) => setName(e.target.value)} required />
        </div>
        <div>
          <label htmlFor="description">Description:</label>
          <input type="text" id="description" value={description} onChange={(e) => setDescription(e.target.value)} required />
        </div>
        <div>
          <label htmlFor="price">Price:</label>
          <input type="number" step="0.01" id="price" value={price} onChange={(e) => setPrice(e.target.value)} required />
        </div>
        <button type="submit">Add Haircut</button>
      </form>
    </div>
  );
};

export default AddHaircut;

