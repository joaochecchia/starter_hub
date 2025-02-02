import { useState, useEffect, useContext } from 'react'
import useFetch from '../hooks/UseFetch.jsx'
import { Link, Navigate } from 'react-router-dom'
import { LoginContext } from '../context/LoginContext.jsx'
import './LoginScreen.css'

const LoginScreen = () => {
  const [Username, setUsername] = useState("")
  const [Password, setPassword] = useState("")
  const [redirect, setRedirect] = useState(false)
  const [errorMessage, setErrorMessage] = useState("")
  const { userToken, decodedToken, changeUserToken, clearUserToken } = useContext(LoginContext)
  const { httpConfig, data, loading, setId, setUrl } = useFetch()

  const handleUsernameChange = (e) => {
    setUsername(e.target.value)
  }

  const handlePasswordChange = (e) => {
    setPassword(e.target.value)
  }

  const handleSubmit = async (e) => {
    console.log("estive aqui")
    e.preventDefault()
    
    setUrl("http://localhost:8080/starter-hub/user/login")

    const loginRequest = {
      username: Username,
      password: Password,
    }
    
    httpConfig("POST", loginRequest)
  }

  useEffect(() => {
    if (data && data.Body && data.Body.token) {
      changeUserToken(data.Body.token)
      setErrorMessage("")
      setRedirect(true)
    }
  }, [data])

  if (redirect) {
    return <Navigate to="/" replace />;
  }

  console.log("a: " + userToken)
  return (
    <div className="LoginContainer">
      <form>
        <div className="labelDiv">
          <label htmlFor="">Username</label>
          <input type="text" onChange={handleUsernameChange} />
        </div>
        <div className="labelDiv">
          <label htmlFor="">Password</label>
          <input type="password" onChange={handlePasswordChange} />
          <Link className='registerLink' to={'/register'}>Don't have an account?</Link>
        </div>
        <input
          className="formButton"
          type="button"
          value={"Sign in"}
          onClick={handleSubmit}
        />
      </form>
    </div>
  )
}

export default LoginScreen
