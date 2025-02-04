import { useState, useContext } from 'react'
import './App.css'
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import { LoginContext } from '../context/LoginContext.jsx'
import LoginScreen from '../Components/LoginScreen'
import Home from '../Components/Home.jsx'
import RegisterUserScreen from '../Components/RegisterUserScreen.jsx'
import RegisterUserProperties from '../Components/RegisterUserProperties.jsx'

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
        <Route path='/register' element={<RegisterUserScreen/>}/>
        <Route path='/register/properties' element={<RegisterUserProperties/>}/>
      </Routes>
    </BrowserRouter>
  )
}

export default App
