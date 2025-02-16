import { useEffect, useState, useContext } from 'react'
import { RepositoriesContext } from '../context/RepositoriesContext'
import UseFetch from '../hooks/UseFetch.jsx'
import style from './EditRepository.module.css'

const EditRepository = ({ repository, token, setEditRepositoryComponent }) => {
    const privacyEnum = {
        public: "PUBLIC",
        private: "PRIVATE"
    }

    const { overwriteRepository } = useContext(RepositoriesContext)

    const { httpConfig, data, errors, loading, setUrl } = UseFetch()

    const [repositoryName, setRepositoryName] = useState(repository?.name || "")
    const [repositoryNameError, setRepositoryNameError] = useState(false)
    const [repositoryErrorMessage, setRepositoryErrorMessage] = useState('')

    const [description, setDescription] = useState(repository?.repositoryDescription || "")
    const [repositoryPath, setRepositoryPath] = useState(repository?.localRepositoryPath || "")
    const [privacy, setPrivacy] = useState(repository?.visibility || privacyEnum.public)

    const handleRepositoryName = (e) => {
        setRepositoryName(e.target.value)
    }

    const handleDescription = (e) => {
        setDescription(e.target.value)
    }

    const handleRepositoryPath = (e) => {
        setRepositoryPath(e.target.value)
    }

    const handlePrivacy = (e, id) => {
        setPrivacy(id === 'public' ? privacyEnum.public : privacyEnum.private)
    }

    const handleSubmit = (e) => {
        e.preventDefault()

        if (repositoryName.length > 3) {
            setRepositoryNameError(false)

            const newRepository = {
                name: repositoryName,
                repositoryDescription: description,
                localRepositoryPath: repositoryPath,
                visibility: privacy,
                userPropertiesID: repository.userPropertiesID
            }

            console.log(newRepository)

            setUrl("http://localhost:8080/starter-hub/users/repository/edit")
            httpConfig("PUT", newRepository, repository.id, token)
        } else {
            setRepositoryNameError(true)
            setRepositoryErrorMessage("Name must have at least 3 characters.")
        }
    }

    useEffect(() => {
        if (errors) {
            console.log(errors)
        } else if (data && data["Message: "] && data["Body: "]) {
            overwriteRepository(data["Body: "])
            setEditRepositoryComponent(false)
        }
    }, [data, errors])

    return (
        <form className={style.createRepositoryContainer} onSubmit={handleSubmit}>
            <div>
                <label style={repositoryNameError ? { color: '#FF6F61' } : {}}>
                    {repositoryNameError ? repositoryErrorMessage : "Repository Name:"}
                </label>
                <input
                    type="text"
                    name="repoName"
                    placeholder='Required'
                    value={repositoryName}
                    onChange={handleRepositoryName}
                />
            </div>
            <div>
                <label>Description:</label>
                <textarea
                    name="description"
                    placeholder='Optional'
                    value={description}
                    onChange={handleDescription}
                ></textarea>
            </div>
            <div>
                <label>Select local path:</label>
                <input
                    type="text"
                    value={repositoryPath}
                    onChange={handleRepositoryPath}
                />
            </div>
            <div className={style.privacyContainer}>
                <div>
                    <input
                        type="radio"
                        id="public"
                        name="privacy"
                        value="public"
                        checked={privacy === privacyEnum.public}
                        onChange={(e) => handlePrivacy(e, e.target.id)}
                    />
                    <label htmlFor="public">Public</label>
                </div>
                <div>
                    <input
                        type="radio"
                        id="private"
                        name="privacy"
                        value="private"
                        checked={privacy === privacyEnum.private}
                        onChange={(e) => handlePrivacy(e, e.target.id)}
                    />
                    <label htmlFor="private">Private</label>
                </div>
            </div>
            <button className={style.createRepositorybutton} type="submit">Save</button>
            {errors && <label>{errors}</label>}
        </form>
    )
}

export default EditRepository