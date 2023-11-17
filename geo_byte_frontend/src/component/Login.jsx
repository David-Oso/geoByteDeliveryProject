import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const baseUrl = "http://localhost:8080/user/login";

function Login() {  
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const formData = {
      email: email,
      password: password,
    };

    await axios.post(baseUrl, formData)
    .then(response => {
        console.log('API Response:', response.data);
    })
    .then (() => {
      navigate("/locations_table");
    })
    .catch(error => {
      alert(error);
        console.error('API Error:', error.response ? error.response.data : 'No response received');
    });
  };

  return (
    <div className="flex items-center justify-center h-screen">
      <div className="bg-gray-100 p-6 rounded-md shadow-md">
        <h2 className="text-2xl font-semibold mb-4 text-center">Login into our application</h2>
        <form className="text-sm" onSubmit={handleSubmit}>
          <div className="mb-4">
            <label htmlFor="email" className="block text-gray-700">Email:</label>
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
              className="border rounded w-full px-3 py-2 mt-1 focus:outline-none focus:ring focus:border-blue-300"
              placeholder="Enter your email"
            />
          </div>
          <div className="mb-4">
            <label htmlFor="password" className="block text-gray-700">Password:</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              className="border rounded w-full px-3 py-2 mt-1 focus:outline-none focus:ring focus:border-blue-300"
              placeholder="Enter your password"
            />
          </div>
          <div className="text-center">
          {/* <Link to="/locations"> */}
              <button
                type="submit"
                className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"
              >
                Login
              </button>
            {/* </Link> */}
          </div>
        </form>
      </div>
    </div>
  );
}

export default Login;
