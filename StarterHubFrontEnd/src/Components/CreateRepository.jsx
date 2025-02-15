import { useEffect, useState, useContext } from 'react'
import { RepositoriesContext } from '../context/RepositoriesContext'
import UseFetch from '../hooks/UseFetch.jsx'
import style from './CreateRepository.module.css'

const CreateRepository = ({token, userPropertiesId, setCreateRepositoryComponent}) => {
    const privacyEnum = {
        public: "PUBLIC",
        private: "PRIVATE"
    }

    const { addNewRepository } = useContext(RepositoriesContext)

    const { httpConfig, data, errors, loading, setUrl, url } = UseFetch()

    const [repositoryName, setRepositoryName] = useState('')
    const [repositoryNameError, setRepositoryNameError] = useState(false)
    const [repositoryErrorMessage, setRepositoryErrorMessage] = useState('')

    const [description, setDescription] = useState('')
    const [privacy, setPrivacy] = useState(privacyEnum.public)

    const [createRepositoryError, setCreateRepositoryError] = useState('')

    const handleRepositoryName = (e) => {
        setRepositoryName(e.target.value)
    }

    const handleDescription = (e) => {
        setDescription(e.target.value)
    }

    const handlePrivacy = (e, id) => {
        setPrivacy(id === 'public' ? privacyEnum.public : privacyEnum.private)
    }

    const handleSubmit = (e) => {
        e.preventDefault()

        if(repositoryName.length > 3){
            setRepositoryNameError(false)

            const newRepository = {
                name: repositoryName,
                repositoryDescription: description,
                visibility: privacy,
                userPropertiesID: userPropertiesId
            }
            
            setUrl("http://localhost:8080/starter-hub/users/repository/create")
            httpConfig("POST", newRepository, null, token)
        } else{
            setRepositoryNameError(true)
            setRepositoryErrorMessage("Name may have 3 minimun characters.")
        }
    }

    useEffect(() => {
         if(errors){
            console.log(errors)
        } else if(data && data["Message: "] && data["Body: "]){
            addNewRepository(data["Body: "])
            setCreateRepositoryComponent(false)
        }
    }, [data, errors])

    return (
        <form className={style.createRepositoryContainer} onSubmit={handleSubmit}>
            <div>
                <label style={repositoryNameError ? { color: '#FF6F61' } : {}}>
                    {repositoryNameError ? repositoryErrorMessage : "Repository Name"}
                </label>
                <input type="text" name="repoName" placeholder='Obrigatory' onChange={handleRepositoryName} />
            </div>
            <div>
                <label>Description</label>
                <textarea name="description" placeholder='Optional' onChange={handleDescription}></textarea>
            </div>
            <div className={style.privacyContainer}>
                <div>
                    <input type="radio" id="public" name="privacy" value="public" onClick={(e) => handlePrivacy(e, e.target.id)} />
                    <label htmlFor="public">Public</label>
                </div>
                <div>
                    <input type="radio" id="private" name="privacy" value="private" onClick={(e) => handlePrivacy(e, e.target.id)} />
                    <label htmlFor="private">Private</label>
                </div>
            </div>
            <button className={style.createRepositorybutton} type="submit">Create</button>
            {errors && <label>{errors}</label>}
        </form>
    )
}

export default CreateRepository
