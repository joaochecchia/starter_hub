import { useEffect, useState, useContext } from "react"
import UseFetch from "../hooks/UseFetch"
import CreateRepository from "./CreateRepository.jsx"
import EditRepository from "./EditRepository.jsx"
import DeleteRepository from "./DeleteRepository.jsx"
import { LoginContext } from "../context/LoginContext"
import { UserPropertiesContext } from "../context/UserPropertiesContext"
import { RepositoriesContext } from "../context/RepositoriesContext"
import styles from "./Home.module.css"

const Home = () => {
    const { userToken, decodedToken } = useContext(LoginContext)
    const { userProperties, changeUserProperties } = useContext(UserPropertiesContext)
    const { repositories, setAllRepositories } = useContext(RepositoriesContext)

    const [notFound, setNotFound] = useState(false)

    const [activeButton, setActiveButton] = useState("Repositories")

    const [createRepositoryComponent, setCreateRepositoryComponent] = useState(false)
    const [showDeleteRepositoryModal, setShowDeleteRepositoryModal] = useState(false)
    const [editRepositoryComponent, setEditRepositoryComponent] = useState(false)
    
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
            setShowDeleteRepositoryModal(true)
        } if (activeButton == "Edit Repository"){
            setSelectedRepository(repository)
            setEditRepositoryComponent(true)
        }
    }

    const handleSidebarButtonSubmit = (btn) => {
        setCreateRepositoryComponent(false)
        setEditRepositoryComponent(false)

        if(btn === "Repositories"){
            setActiveButton("Repositories")
        } else if (btn === "Edit Repository"){
            setActiveButton("Edit Repository")
        } else if(btn === "Delete Repository"){
            setActiveButton("Delete Repository")
        } else if(btn === "New Repository"){
            setActiveButton("New Repository")
            setCreateRepositoryComponent(true)
        }
    }

    const handleCloseModal = () => {
        setShowDeleteRepositoryModal(false)
        setActiveButton("Repositories")
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
                            onClick={() => handleSidebarButtonSubmit(btn)}
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
                    ) : editRepositoryComponent ? (
                        <EditRepository
                            repository={selectedRepository}
                            token={userToken}
                            setEditRepositoryComponent={setEditRepositoryComponent}
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

            {showDeleteRepositoryModal && (
                <DeleteRepository repository={selectedRepository} onClose={handleCloseModal} token={userToken} />
            )}
        </div>
    )
}

export default Home