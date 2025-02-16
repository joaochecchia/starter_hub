import { useState, useEffect, useContext } from "react"
import UseFetch from "../hooks/UseFetch"
import { RepositoriesContext } from "../context/RepositoriesContext"
import styles from "./DeleteRepository.module.css"

const DeleteRepository = ({ repository, onClose, token }) => {
    const [verifyMessage, setVerifyMessage] = useState("")
    const [verifyMessageError, setVerifyMessageError] = useState(false)
    const { removeRepositories } = useContext(RepositoriesContext)
    const { httpConfig, data, errors, setUrl } = UseFetch()

    const handleChange = (e) => {
        setVerifyMessage(e.target.value)
    }

    const handleSubmit = () => {
        const verify = `delete ${repository.name}`
        setVerifyMessageError(false)

        if (verify === verifyMessage) {
            console.log("Correto")
            setUrl("http://localhost:8080/starter-hub/users/repository/delete")
            httpConfig("DELETE", null, repository.id, token)
        } else {
            console.log("Errado")
            setVerifyMessageError(true)
            removeRepositories(repository)
        }
    }

    useEffect(() => {
        if (data && data["Message: "] && data["Body: "]) {
            console.log("Repositório deletado com sucesso.")
            onClose() // Fecha a modal e retorna para a Home
        }
    }, [data])

    return (
        <div className={styles.modalOverlay}>
            <div className={styles.modalContent}>
                <h3>Confirm Delete</h3>
                <div>
                    <label htmlFor="deleteConfirm" style={verifyMessageError ? { color: "#FF6F61" } : {}}>
                        Type: delete {repository.name}
                    </label>
                    <input type="text" id="deleteConfirm" onChange={handleChange} />
                </div>
                <div className={styles.buttonsContainer}>
                    <button onClick={handleSubmit}>Delete</button>
                    <button onClick={onClose}>Cancel</button>
                </div>
            </div>
        </div>
    )
}

export default DeleteRepository
