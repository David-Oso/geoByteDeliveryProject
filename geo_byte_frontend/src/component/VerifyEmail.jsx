import React, {useState}from 'react'
import PinInput from "react-pin-input";
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';

const baseUrl = "http://localhost:8080/user/verify";

function VerifyEmail() {
  const { userId } = useParams;
  const [otp, setOtp] = useState('');
  const navigate = useNavigate();

  const handleOtpChange = (value, index) => {
    const newOtp = otp.substring(0, index) + value + otp.substring(index + 1);
    setOtp(newOtp);
  };

  const otp_input = {
    width: "4rem",
    height: "4rem",
    margin: "0.5rem",
    border: "1px solid #ccc",
    outline: "none",
    "border-radius": "0.25rem",
    "text-align": "center",
    "font-size": "1.5rem"
  }

  const otp_input_focus = {
    "border-color": "#3498db"
  }

  const handleSubmit = async () => {
    const formData = {
      otp: otp,
      userId: userId
    }
    try {
      const response = await axios.post(baseUrl, formData);
      console.log('API Response:', response.data);
      navigate("/locations_table");
    } catch (error) {
      alert(error);
      console.error('API Error:', error.response ? error.response.data : 'No response received');
    };
  };

  return (
    <div className="flex flex-col items-center justify-center h-screen">
      <div className="text-center text-2xl font-semibold mb-4">
        Enter the OTP to verify you email
      </div>
      <PinInput
        length={5}
        initialValue=""
        onChange={handleOtpChange}
        focus
        inputStyle={otp_input }
        inputFocusStyle={otp_input_focus}
        regexCriteria={/^\d{5}$/}
      />
      <button
        className="mt-4 px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-700"
        onClick={handleSubmit}>
        Verify Email
      </button>    
    </div>
  )
}

export default VerifyEmail;
