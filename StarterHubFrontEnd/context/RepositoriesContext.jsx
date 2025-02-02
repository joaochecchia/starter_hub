import { createContext, useState } from "react"

export const RepositoriesContext = createContext()

export const RepositoriesContextProvider = ({ children }) => {
    const [repositories, setRepositories ] = useState([])

    const setAllRepositories = (repositories) => {
        setRepositories(repositories)
    }

    const addNewRepository = (repository) => {
        setRepositories((prev) => [...prev, repository])
    }

    const removeRepositorie = (repository) => {
        setRepositories((prev) => prev.filter((item) => item !== repository))
    }

    const clearRepository = () => {
        setRepositories([])
    }

    const repositoryContext = {
        repositories,
        setAllRepositories,
        addNewRepository,
        removeRepositorie,
        clearRepository
    }

    return(
        <RepositoriesContext.Provider value={repositoryContext}>
            { children }
        </RepositoriesContext.Provider>
    )
}