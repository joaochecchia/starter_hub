import { useEffect, useState, useContext } from 'react'
import UseFetch from '../hooks/UseFetch'
import CreateRepository from './CreateRepository.jsx'
import { LoginContext } from '../context/LoginContext'
import { UserPropertiesContext } from '../context/UserPropertiesContext'
import { RepositoriesContext } from '../context/RepositoriesContext'
import styles from './Home.module.css'

const Home = () => {
    const { userToken, decodedToken } = useContext(LoginContext)
    const { userProperties, changeUserProperties } = useContext(UserPropertiesContext)
    const { repositories, setAllRepositories } = useContext(RepositoriesContext)
    
    const [notFound, setNotFound] = useState(false) 
    const [activeButton, setActiveButton] = useState(null)

    const [createRepositoryComponent, setCreateRepositoryComponent] = useState(false)

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
                console.log("No if da url")
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
            console.log("estive no not found")
            setNotFound(true)
        }
    }, [repositories, loading])

    const handleButtonClick = (buttonName) => {
        setActiveButton(buttonName)

        if(buttonName === 'New Repository'){
            setCreateRepositoryComponent(true)
        } else {
            setCreateRepositoryComponent(false) 
        }
    }

    return (
        <div className={styles.homeContainer}>
            <div className={styles.navBar}>
                <button>UserPhoto</button>
                <input type="text" className={styles.search} name='query' placeholder='Search sei la oq' />
            </div>
            <div className={styles.container}>
                <div className={styles.sideBarContainer}>
                    {['Repositories', 'New Repository', 'Edit Repository', 'Delete Repository', 'Edit Profile', 'Likes', 'Followers', 'History'].map((btn) => (
                        <button
                            key={btn}
                            className={activeButton === btn ? styles.activeButton : ''}
                            onClick={() => handleButtonClick(btn)}
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
                        />
                    ) : loading ? (
                        <h3>Loading...</h3>
                    ) :  (
                        repositories.map((item) => (
                            <button key={item.id}>{item.name}</button>
                        ))
                    )}
                </div>
            </div>
        </div>
    )
}

export default Home
