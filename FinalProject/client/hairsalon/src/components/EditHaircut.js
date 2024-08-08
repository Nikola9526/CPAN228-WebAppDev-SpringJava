import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import './EditHaircut.css';

const EditHaircut = () => {
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [price, setPrice] = useState('');
  const { id } = useParams(); // Get the haircut ID from the URL
  const navigate = useNavigate();

  useEffect(() => {
    const fetchHaircut = async () => {
      try {
        const response = await fetch(`http://localhost:8080/haircuts/api/allCuts/${id}`);
        const data = await response.json();
        setName(data.name);
        setDescription(data.description);
        setPrice(data.price);
      } catch (error) {
        console.error('Error fetching haircut details:', error);
      }
    };

    fetchHaircut();
  }, [id]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const updatedHaircut = {
      name,
      description,
      price: parseFloat(price),
    };

    try {
      const response = await fetch(`http://localhost:8080/haircuts/api/allCuts/${id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedHaircut),
      });

      if (response.ok) {
        navigate('/admin-panel');
      } else {
        console.error('Failed to update haircut');
      }
    } catch (error) {
      console.error('Error updating haircut:', error);
    }
  };

  return (
    <div className="edit-haircut">
      <h2>Edit Haircut</h2>
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
        <button type="submit">Update Haircut</button>
      </form>
    </div>
  );
};

export default EditHaircut;
