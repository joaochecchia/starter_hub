import { useEffect, useState, useContext } from 'react'
import UseFetch from '../hooks/UseFetch'
import { LoginContext } from '../context/LoginContext'
import { UserPropertiesContext } from '../context/UserPropertiesContext'
import { RepositoriesContext } from '../context/RepositoriesContext'
import styles from './Home.module.css'
const Home = () => {
    const { userToken, decodedToken, changeUserToken, clearUserToken } = useContext(LoginContext)
    const { userProperties, changeUserProperties, clearUserProperties } = useContext(UserPropertiesContext)
    const { repositories, setAllRepositories, addNewRepository, removeRepositorie, clearRepository } = useContext(RepositoriesContext)
    const { httpConfig, data, loading, setId, setUrl, url } = UseFetch()
    
    useEffect(() => {
        console.log("Estive aqui")
        setUrl(`http://localhost:8080/starter-hub/users/searchByUser/${decodedToken.id}`)
        httpConfig("GET", null, null, userToken)
    }, [decodedToken, userToken])

    useEffect(() => {
        if (userProperties?.id) {
            const newUrl = `http://localhost:8080/starter-hub/users/repository/findAll/45cc4ff8-1488-44e4-89fc-86a141b62010`
            if (url !== newUrl) {
                setUrl(newUrl)
                httpConfig("GET", null, null, userToken)
            }
        }
    }, [userProperties?.id])

    useEffect(() => {
        if (url.includes(`/users/searchByUser/`) && data && data["Body: "]) {
            changeUserProperties(data["Body: "])
        } else if (url.includes(`/repository/findAll/`) && Array.isArray(data) && data.length > 0) {
            setAllRepositories(data)
        }
    }, [data])
    
    return(
        <div className={styles.homeContainer}>
            <input type={styles.search} name='query' placeholder='Search sei la oq'/>
            <div className={styles.container}>
                <div className={styles.sideBarContainer}>
                    <button>klsjdjaljkdjasldjal</button>
                </div>
                <div className={styles.repositoriesContainer}>
                    {loading === true ? (<h3>Loading...</h3>) : (
                        repositories &&  repositories.map((item) => (
                            <button key={item.id}>{item.name}</button>
                        ))
                    )}
                </div>
            </div>
        </div>
    )
}
export default Home