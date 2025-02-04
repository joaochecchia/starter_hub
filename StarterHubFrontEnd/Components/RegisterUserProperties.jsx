import { useEffect, useState } from 'react'
import './RegisterUserProperties.css'
import UseFetch from '../hooks/UseFetch'

const RegisterUserProperties = () => {

    const [description, setDescription] = useState('')

    const [company, setCompany] = useState('')

    const [country, setCountry] = useState('')

    const [postalCode, setPostalCode] = useState('')
    
    const [location, setLocation] = useState('')

    const [links, setLinks] = useState([]) 
    const [maxLinksWarning, setMaxLinksWarning] = useState(false)

    const [userPropertiesID, setUsePropertiesID] = useState(null)


    const { httpConfig, data, errors, loading, setId, setUrl, url } = UseFetch()

    const handleAddLinks = () => {
        if (links.length < 3) { 
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
        const userProperties = {
            description: description,
            company: company,
            usersId: id
        }
        
        console.log(userProperties)

        setUrl("http://localhost:8080/starter-hub/users/create")
        httpConfig("POST", userProperties, null, token)
    }

    useEffect(() => {
        if (data && data["Body: "] && data["Body: "].id) {
            console.log("data no user: " + data["Body: "].id)
            setUsePropertiesID(data["Body: "].id)
            
        }
    }, [data, errors])

    useEffect(() => {
        if(userPropertiesID){
            console.log(userPropertiesID)

            const address = {
                country: country,
                postalCode: postalCode,
                location: location,
                userPropertiesID: userPropertiesID
            }
    
            console.log(address)
            setUrl("http://localhost:8080/starter-hub/users/address/create")
            httpConfig("POST", address, null, token)
            console.log(data)
            console.log(errors)
        }
    },[userPropertiesID])

    return (
        <>
            <div className="container">
                <form className="registerContainer">
                    <div>
                        <div className='labelContainer'>
                            <label>Description</label>
                            <label style={description.length === 255 ? { color: '#FF6F61' } : {}} >Size: {description.length}</label>
                        </div>
                        <textarea onChange={handleTextAreaChange} maxLength={255} placeholder='Optional'></textarea>
                    </div>
                    <div>
                        <label>Company</label>
                        <input type="text" placeholder='Optional' onChange={handleCompany} />
                    </div>
                    <div className='divisory'></div>
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

                    <div className='divisory'></div>

                    <div className='linksContainer'>
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
                            <div className='buttonsDiv'>
                                <button type="button" onClick={handleAddLinks}>+</button>
                                {links.length > 0 && (
                                    <button type="button" onClick={() => handleRemoveLinks(links.length - 1)}>-</button>
                                )}
                            </div>
                        </div>
                    </div>

                    <div className="buttonDiv">
                        <input type="button" value="register" onClick={handleSubmit}/>
                    </div>
                </form>
            </div>
        </>
    )
}

export default RegisterUserProperties
