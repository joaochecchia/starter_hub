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

    const overwriteRepository = (repository) => {
        setAllRepositories((prev) => prev.filter((item) => item.id !== repository.id))
        addNewRepository(repository)
    }

    const removeRepositories = (repositoryId) => {
        setRepositories((prev) => prev.filter((item) => item.id !== repositoryId))
    }

    const clearRepository = () => {
        setRepositories([])
    }

    const repositoryContext = {
        repositories,
        setAllRepositories,
        addNewRepository,
        overwriteRepository,
        removeRepositories,
        clearRepository
    }

    return(
        <RepositoriesContext.Provider value={repositoryContext}>
            { children }
        </RepositoriesContext.Provider>
    )
}