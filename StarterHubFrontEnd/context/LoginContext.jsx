import { jwtDecode } from "jwt-decode"
import { createContext, useState } from "react"

export const LoginContext = createContext()

export const LoginContextProvider = ({ children }) => {
    const [userToken, setUserToken] = useState(null)
    const [decodedToken, setDecodedToken] = useState(null)

    const changeUserToken = (token) => {
        try {
            const decoded = jwtDecode(token)
            setUserToken(token)
            setDecodedToken(decoded) 
        } catch (error) {
            console.error("ERROR decoding token:", error)
            setUserToken(null)
            setDecodedToken(null) 
        }
    }

    const clearUserToken = () => {
        setUserToken(null) 
        setDecodedToken(null) 
    }

    const loginContext = {
        userToken,
        decodedToken,
        changeUserToken,
        clearUserToken,
    }

    return (
        <LoginContext.Provider value={loginContext}>
            {children}
        </LoginContext.Provider>
    )
}