import { Children, createContext, useState } from "react";

export const UserPropertiesContext = createContext()

export const UserPropertiesContextProvider = ({ children }) => {
    const [userProperties, setUserProperties] = useState(null)

    const changeUserProperties = (userProperties) => {
        setUserProperties(userProperties)
    }

    const clearUserProperties = () => {
        setUserProperties([])
    }

    const userPropertiesContext = {
        userProperties,
        changeUserProperties,
        clearUserProperties
    }

    return(
        <UserPropertiesContext.Provider value={userPropertiesContext}>
            { children }
        </UserPropertiesContext.Provider>
    )
}