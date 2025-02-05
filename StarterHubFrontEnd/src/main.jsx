import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import { LoginContextProvider } from './context/LoginContext.jsx'
import { RepositoriesContextProvider } from './context/RepositoriesContext.jsx'
import { UserPropertiesContextProvider } from './context/UserPropertiesContext.jsx' 

createRoot(document.getElementById('root')).render(
  <StrictMode>
      <LoginContextProvider>
        <UserPropertiesContextProvider>
          <RepositoriesContextProvider>
            <App />
          </RepositoriesContextProvider>
        </UserPropertiesContextProvider>
      </LoginContextProvider>  
  </StrictMode>
);
