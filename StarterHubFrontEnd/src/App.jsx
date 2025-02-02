import { useState, useContext } from 'react';
import './App.css';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { LoginContext } from '../context/LoginContext.jsx';
import LoginScreen from '../Components/LoginScreen';
import Home from '../Components/Home.jsx';

function App() {
  const { userToken, decodedToken, changeUserToken, clearUserToken } = useContext(LoginContext)

  return (
    <BrowserRouter>
      <Routes>
        <Route
          path="/"
          element={userToken === null ? <Navigate to="/login" replace /> : <Home />}
        />
        <Route path="/login" element={<LoginScreen/>} />
      </Routes>
    </BrowserRouter>
  )
}

export default App;
