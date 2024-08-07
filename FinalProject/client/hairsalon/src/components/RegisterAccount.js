import React, { useState } from 'react';
import './Login.css';

const RegisterAccount = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    // Store user details in localStorage
    localStorage.setItem('user', JSON.stringify({ username, password }));
    console.log('User registered:', username);
  };

  return (
    <div className="login-container">
      <form onSubmit={handleSubmit}>
        <h2>Register</h2>
        <div className="input-group">
          <label htmlFor="username">Username:</label>
          <input
            type="text"
            id="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div className="input-group">
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <button type="submit">Register</button>
      </form>
    </div>
  );
};

export default RegisterAccount;

