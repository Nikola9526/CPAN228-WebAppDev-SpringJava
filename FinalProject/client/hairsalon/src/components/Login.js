import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Login.css';

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [showPopup, setShowPopup] = useState(false);
  const navigate = useNavigate();

  // Initialize admin credentials in localStorage (can be done elsewhere in the app setup)
  React.useEffect(() => {
    if (!localStorage.getItem('adminUser')) {
      localStorage.setItem('adminUser', JSON.stringify({ username: 'admin', password: 'pass' }));
    }
    if (!localStorage.getItem('users')) {
      localStorage.setItem('users', JSON.stringify([])); // Initialize empty user array
    }
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();

    const admin = JSON.parse(localStorage.getItem('adminUser'));
    const users = JSON.parse(localStorage.getItem('users'));

    // Check if login details match admin credentials
    if (username === admin.username && password === admin.password) {
      localStorage.setItem('currentUser', 'admin');
      setShowPopup(true);
      setTimeout(() => navigate('/admin-panel'), 2000); // Redirect after showing the popup
    } else if (users.some(user => user.username === username && user.password === password)) {
      localStorage.setItem('currentUser', username);
      setShowPopup(true);
      setTimeout(() => navigate('/homepage'), 2000); // Redirect after showing the popup
    } else {
      alert('Invalid credentials');
    }
  };

  return (
    <div className="login-container">
      <form onSubmit={handleSubmit}>
        <h2>Login</h2>
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
        <button type="submit" className="login-button">Login</button>
        <Link to="/register" className="register-link">
          <button type="button" className="register-button">Don't have an Account? Register!</button>
        </Link>
      </form>
      <Link to="/admin-panel" className="admin-link">
        <img src="/adminbutton.png" alt="Admin Panel" className="admin-button" />
      </Link>

      {showPopup && (
        <div className="popup">
          <div className="popup-content">
            <span className="close" onClick={() => setShowPopup(false)}>&times;</span>
            <h2>Welcome, {username}!</h2>
          </div>
        </div>
      )}
    </div>
  );
};

export default Login;









