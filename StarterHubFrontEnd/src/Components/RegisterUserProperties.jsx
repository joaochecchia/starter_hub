import { useEffect, useState } from 'react'
import { Navigate, redirect, useLocation } from 'react-router'
import styles from './RegisterUserProperties.module.css'
import UseFetch from '../hooks/UseFetch.jsx'

const RegisterUserProperties = () => {
    
    const locationHook = useLocation()
    const user = locationHook.state?.user

    const [description, setDescription] = useState('')

    const [company, setCompany] = useState('')

    const [country, setCountry] = useState('')

    const [postalCode, setPostalCode] = useState('')
    
    const [location, setLocation] = useState('')

    const [links, setLinks] = useState([]) 
    const [maxLinksWarning, setMaxLinksWarning] = useState(false)
    const [linkFormatError, setLinkFormatError] = useState([])

    const [userPropertiesID, setUsePropertiesID] = useState(null)

    const [addressSaved, setAddressSaved] = useState(false)

    const [hasError, setHasError] = useState(false)
    const [errorMessage, setErrorMessage] = useState('')

    const [redirect, setRedirect] = useState(false);

    const { httpConfig, data, errors, loading, setId, setUrl, url } = UseFetch()

    const handleAddLinks = () => {
        if (links.length < 3) { 
            // aplicar regex
            const testLink = /^(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w \.-]*)*\/?$/

            
            setLinks((prevLinks) => [...prevLinks, ""])  
        } else {
            setMaxLinksWarning(true) 
        }
    }
    
    const handleRemoveLinks = (index) => {
        setLinks(links.filter((_, i) => i !== index)) 
    }

    const handleLinkChange = (index, value) => {
        const updatedLinks = [...links]
        updatedLinks[index] = value 
        setLinks(updatedLinks)
    }

    const handleTextAreaChange = (e) => {
        setDescription(e.target.value)
    }

    const handleCountry = (e) => {
        setCountry(e.target.value)
    }

    const handlePostalCode = (e) => {
        let value = e.target.value
        const inputType = e.nativeEvent.inputType
    
        value = value.replace(/\D/g, '')
    
        if (inputType !== 'deleteContentBackward') {
            if (value.length > 2) {
                value = `${value.slice(0, 2)}.${value.slice(2)}`
            }
            if (value.length > 6) {
                value = `${value.slice(0, 6)}-${value.slice(6)}` 
            }
        }
    
        if (value.length <= 10) {
            setPostalCode(value)
        }
    }

    const handleLocation = (e) => {
        setLocation(e.target.value)
    }
    

    const handleCompany = (e) => {
        setCompany(e.target.value)
    }

    const handleSubmit = () => {
        const registerRequest = {
            userRequest: user,
            description: description,
            company: company,
            country: country,
            postalCode: postalCode,
            location: location,
            createLinksRequest: links
        }
        
        console.log(registerRequest)

        setUrl("http://localhost:8080/starter-hub/user/register")
        httpConfig("POST", registerRequest, null, null)
    }

    useEffect(() => {
        if (data && data["Body: "]) {
            console.log(data["Body: "])
            setRedirect(true)
        } else if(errors){
            console.log(errors)
        }
    }, [data, errors])

    if(redirect){
        return <Navigate to="/" replace/>
    }

    return (
        <>
            <div className={styles.container}>
                <form className={styles.registerContainer}>
                    <div>
                        <div className={styles.labelContainer}>
                            <label>Description</label>
                            <label style={description.length === 255 ? { color: '#FF6F61' } : {}} >Size: {description.length}</label>
                        </div>
                        <textarea onChange={handleTextAreaChange} maxLength={255} placeholder='Optional'></textarea>
                    </div>
                    <div>
                        <label>Company</label>
                        <input type="text" placeholder='Optional' onChange={handleCompany} />
                    </div>
                    <div className={styles.divisory}></div>
                    <label>Address: Optional</label>
                    <div>
                        <label>Country</label>
                        <input type="text" onChange={handleCountry}/>
                    </div>
                    <div>
                        <label>Postal code</label>
                        <input type="text" value={postalCode} onChange={handlePostalCode} />
                    </div>
                    <div>
                        <label>Location</label>
                        <input type="text" onChange={handleLocation} />
                    </div>

                    <div className={styles.divisory}></div>

                    <div className={styles.linksContainer}>
                        {maxLinksWarning ? <p style={{color: '#FF6F61'}}>You can only add 3 links!</p> : <label>Links: </label>}
                        <div>
                            {links.map((link, index) => (
                                <div key={index} className="linkInput">
                                    <input
                                        type="text"
                                        value={link}
                                        onChange={(e) => handleLinkChange(index, e.target.value)}
                                        placeholder="Optional"
                                    />
                                </div>
                            ))}
                            <div className={styles.buttonsDiv}>
                                <button type="button" onClick={handleAddLinks}>+</button>
                                {links.length > 0 && (
                                    <button type="button" onClick={() => handleRemoveLinks(links.length - 1)}>-</button>
                                )}
                            </div>
                        </div>
                    </div>

                    <div className={styles.buttonDiv}>
                        <input type="button" value="register" onClick={handleSubmit}/>
                    </div>
                </form>
            </div>
        </>
    )
}

export default RegisterUserProperties
