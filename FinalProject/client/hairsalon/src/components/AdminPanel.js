import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './AdminPanel.css';

const AdminPanel = () => {
  const [haircuts, setHaircuts] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchHaircuts = async () => {
      try {
        const response = await fetch('http://localhost:8080/haircuts/api/allCuts');
        const data = await response.json();
        setHaircuts(data);
      } catch (error) {
        console.error('Error fetching haircuts:', error);
      }
    };

    fetchHaircuts();
  }, []);

  const handleDelete = async (id) => {
    try {
      const response = await fetch(`http://localhost:8080/haircuts/api/allCuts/${id}`, {
        method: 'DELETE',
      });

      if (response.ok) {
        setHaircuts(haircuts.filter((haircut) => haircut.id !== id));
      } else {
        console.error('Failed to delete haircut');
      }
    } catch (error) {
      console.error('Error deleting haircut:', error);
    }
  };

  const handleEdit = (id) => {
    navigate(`/editHaircut/${id}`);
  };

  return (
    <div className="admin-panel">
      <h1>Haircut Services - Admin Panel</h1>
      <button onClick={() => navigate('/addHaircut')} className="add-button">Add Haircut</button>
      <table className="services-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price ($)</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {haircuts.map((haircut) => (
            <tr key={haircut.id}>
              <td>{haircut.id}</td>
              <td>{haircut.name}</td>
              <td>{haircut.description}</td>
              <td>{haircut.price}</td>
              <td>
                <button onClick={() => handleEdit(haircut.id)} className="edit-button">Edit</button>
                <button onClick={() => handleDelete(haircut.id)} className="delete-button">Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AdminPanel;

