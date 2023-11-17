import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

const baseUrl = "http://localhost:8080/user/register";

function SignUp() {
  const [email, setEmail] = useState('');
  const [name, setName] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();
  
  const handleSignUp = async () => {
    const formData = {
      email: email,
      name: name,
      password: password,
    };

    await axios.post(baseUrl, formData)
      .then(response => {
        const userId = response.data.userId;
        navigate(`/verify_email/${userId}`);
          console.log('API Response:', response.data);
      })
      // .then (() => {
      // })
      .catch(error => {
        alert(error);
          console.error('API Error:', error.response ? error.response.data : 'No response received');
      });
  };

  return (
    <div className="flex items-center justify-center h-screen">
      <div className="bg-gray-100 p-6 rounded-md shadow-md">
        <h2 className="text-2xl font-semibold mb-4 text-center">Sign Up to our application now</h2>
        <form className="text-sm">
          <div className="mb-4">
            <label className="block text-gray-700">Email:</label>
            <input
              className="w-full p-2 border rounded"
              placeholder="Enter email:"
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>

          <div className="mb-4">
            <label className="block text-gray-700">Name:</label>
            <input
              className="w-full p-2 border rounded"
              placeholder="Enter name: "
              type="text"
              value={name}
              onChange={(e) => setName(e.target.value)}
              required
            />
          </div>

          <div className="mb-4">
            <label className="block text-gray-700">Password:</label>
            <input
              className="w-full p-2 border rounded"
              placeholder="Enter password: "
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>

          <div className="text-center">
          {/* <Link to="/locations"> */}
              <button
                className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"
                type="button"
                onClick={handleSignUp}
              >
                Sign Up
              </button>
            {/* </Link> */}
          </div>
        </form>
        <p className="mt-4 text-center text-gray-600">
          Already have an account?{'   '}
            <Link to="/log-in" className="text-blue-500 hover:underline">
              Login
            </Link>
        </p>
      </div>
    </div>
  );
}

export default SignUp;
