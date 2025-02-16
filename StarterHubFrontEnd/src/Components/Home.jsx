import { useEffect, useState, useContext } from "react"
import UseFetch from "../hooks/UseFetch"
import CreateRepository from "./CreateRepository.jsx"
import DeleteRepository from "./DeleteRepository.jsx"
import { LoginContext } from "../context/LoginContext"
import { UserPropertiesContext } from "../context/UserPropertiesContext"
import { RepositoriesContext } from "../context/RepositoriesContext"
import styles from "./Home.module.css"

const Home = () => {
    //depois corrigir a atualização dos repositorios depois de deletado
    const { userToken, decodedToken } = useContext(LoginContext)
    const { userProperties, changeUserProperties } = useContext(UserPropertiesContext)
    const { repositories, setAllRepositories } = useContext(RepositoriesContext)

    const [notFound, setNotFound] = useState(false)
    const [activeButton, setActiveButton] = useState("Repositories")
    const [createRepositoryComponent, setCreateRepositoryComponent] = useState(false)
    const [showModal, setShowModal] = useState(false)
    const [selectedRepository, setSelectedRepository] = useState(null)

    const { httpConfig, data, loading, setUrl, url } = UseFetch()

    useEffect(() => {
        if (decodedToken?.id) {
            setUrl(`http://localhost:8080/starter-hub/users/searchByUser/${decodedToken.id}`)
            httpConfig("GET", null, null, userToken)
        }
    }, [decodedToken, userToken])

    useEffect(() => {
        if (userProperties?.id) {
            const newUrl = `http://localhost:8080/starter-hub/users/repository/findAll/${userProperties.id}`
            if (url !== newUrl) {
                setUrl(newUrl)
                httpConfig("GET", null, null, userToken)
            }
        }
    }, [userProperties?.id])

    useEffect(() => {
        if (data) {
            if (url.includes(`/users/searchByUser/`) && data["Body: "]) {
                changeUserProperties(data["Body: "])
            } else if (url.includes(`/repository/findAll/`) && data["Body: "]) {
                setAllRepositories(data["Body: "])
            }
        }
    }, [data])

    useEffect(() => {
        if (!loading && repositories.length === 0) {
            setNotFound(true)
        }
    }, [repositories, loading])

    const handleRepositoryButton = (repository) => {
        if (activeButton === "Delete Repository") {
            setSelectedRepository(repository)
            setShowModal(true)
        }
    }

    const handleCloseModal = () => {
        setShowModal(false)
        setActiveButton("Repositories") // Retorna para a Home
    }

    return (
        <div className={styles.homeContainer}>
            <div className={styles.navBar}>
                <button>UserPhoto</button>
                <input type="text" className={styles.search} name="query" placeholder="Search repositories" />
            </div>
            <div className={styles.container}>
                <div className={styles.sideBarContainer}>
                    {["Repositories", "New Repository", "Edit Repository", "Delete Repository", "Edit Profile", "Likes", "Followers", "History"].map((btn) => (
                        <button
                            key={btn}
                            className={activeButton === btn ? styles.activeButton : ""}
                            onClick={() => setActiveButton(btn)}
                        >
                            {btn}
                        </button>
                    ))}
                </div>
                <div className={styles.repositoriesContainer}>
                    {createRepositoryComponent ? (
                        <CreateRepository
                            token={userToken}
                            userPropertiesId={userProperties.id}
                            setCreateRepositoryComponent={setCreateRepositoryComponent}
                        />
                    ) : activeButton === "Repositories" || activeButton === "Edit Repository" || activeButton === "Delete Repository" ? (
                        loading ? (
                            <h3>Loading...</h3>
                        ) : (
                            repositories.map((item) => (
                                <button key={item.id} onClick={() => handleRepositoryButton(item)}>{item.name}</button>
                            ))
                        )
                    ) : null}
                </div>
            </div>

            {showModal && (
                <DeleteRepository repository={selectedRepository} onClose={handleCloseModal} token={userToken} />
            )}
        </div>
    )
}

export default Home
