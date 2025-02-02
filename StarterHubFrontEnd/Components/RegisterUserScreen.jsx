import { useEffect, useState } from 'react'
import UseFetch from '../hooks/UseFetch'
import './RegisterUserScreen.css'

const RegisterUserScreen = () => {
    
    const [username, setUsername] = useState('')
    const [usernameValid, setUsernameValid] = useState(true)
    const [usernameExists, setUsernameExists] = useState(false)

    const [email, setEmail] = useState(null)
    const [emailValid, setEmailValid] = useState(true)
    const [emailExists, setEmailExists] = useState(false)

    const [phoneNumber, setPhoneNumber] = useState('')
    const [phoneNumberValid, setPhoneNumberValid] = useState(true)
    const [phoneNumberExists, setPhoneNumberExists] = useState(false)

    const [password, setPassword] = useState('')
    const [passwordValid, setPasswordValid] = useState(true)

    const [verifyPassword, setVerifyPassword] = useState('')
    const [verifyPasswordValid, setVerifyPasswordValid] = useState(true)

    const { httpConfig, data, errors, loading, setId, setUrl, url } = UseFetch()

    const handleUsernameChange = (e) => {
        setUsername(e.target.value)
    }

    const handleEmailChange = (e) => {
        setEmail(e.target.value)
    }

    const handlePhoneNumberChange = (e) => {
        let value = e.target.value
        const inputType = e.nativeEvent.inputType

        value = value.replace(/\D/g, '')

        if(inputType !== 'deleteContentBackward'){
            if (value.length <= 2) {
                value = `(${value}`
            } else if (value.length <= 6) {
                value = `(${value.slice(0, 2)}) ${value.slice(2)}`
            } else if (value.length <= 10) {
                value = `(${value.slice(0, 2)}) ${value.slice(2, 7)}-${value.slice(7)}`
            } else {
                value = `(${value.slice(0, 2)}) ${value.slice(2, 7)}-${value.slice(7, 11)}`
            }    
        }

        setPhoneNumber(value)
    }

    const handlePasswordChange = (e) => {
        setPassword(e.target.value)
    }

    const handleVerifyPasswordChange = (e) => {
        setVerifyPassword(e.target.value)        
    }

    const handleSubmit = () => {
        const usernameRegex = /^[a-zA-Z][a-zA-Z0-9]{0,24}$/
        const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/
        const passwordRegex = /^[A-Z0-9@][a-zA-Z0-9.-_]{3,24}$/
        
        if(usernameRegex.test(username) && emailRegex.test(email) && phoneNumber.length === 15 && passwordRegex.test(password) && verifyPassword.length > 0){
            if(password === verifyPassword){
                const newUser = {
                    username: username,
                    password: password,
                    email: email,
                    phone: phoneNumber 
                }
                
                setUrl('http://localhost:8080/starter-hub/user/register')
                httpConfig("POST", newUser)
            } else{
                setVerifyPasswordValid(false)
            }
        } else{
            !usernameRegex.test(username) ? setUsernameValid(false) : setUsernameValid(true)
            !emailRegex.test(email) ? setEmailValid(false) : setEmailValid(true)
            phoneNumber.length < 15 ? setPhoneNumberValid(false) : setPhoneNumberValid(true)
            !passwordRegex.test(password) ? setPasswordValid(false) : setPassword(true)
        }
    }

    useEffect(() => {
        if(data){
            console.log(data)
        } else if(errors){
            console.log(errors)

            setUsernameExists(false)
            setEmailExists(false)
            setPhoneNumberExists(false)

            const errorsArray = errors.split('\n')
            const temp = errorsArray.filter((item) => item != '')
            console.log(temp)
            temp.forEach((item) => {
                if (item.includes("Username")) {
                    setUsernameExists(true); 
                }

                if (item.includes("Email")) {
                    setEmailExists(true);
                }

                if (item.includes("Phone number")) {
                    setPhoneNumberExists(true); 
                }
            })

            console.log(usernameExists)
            console.log(emailExists)
            console.log(phoneNumberExists)
        }
    }, [data, errors])
    
    return (
        <>
            <div className="container">
                <form className="registerContainer">
                    <div>
                        {usernameValid ? usernameExists ? <label style={{ color: '#FF6F61' }} >Username already exists</label> : <label>Username</label> : <label style={{ color: '#FF6F61' }}>Username starts with letters</label>}
                        <input type="text" placeholder='Required' onChange={handleUsernameChange}/>
                    </div>
                    <div>
                        {emailValid ? emailExists ? <label style={{ color: '#FF6F61' }}>Email already exists</label> : <label>Email</label> : <label style={{ color: '#FF6F61' }}>Please, insert a valid email</label>}
                        <input type="email" placeholder='Required' onChange={handleEmailChange}/>
                    </div>
                    <div>
                        {phoneNumberValid ? phoneNumberExists? <label style={{ color: '#FF6F61' }}>Phone number already exists</label> : <label>Phone Number</label> : <label style={{ color: '#FF6F61' }}>Please, insert a valid phone number</label>}
                        <input type="text" placeholder='Required' value={phoneNumber} onChange={handlePhoneNumberChange}/>
                    </div>
                    <div>
                        {passwordValid ? <label>Password</label> : <label style={{ color: '#FF6F61' }}>Password starts with A-Z</label>}
                        <input type="text" placeholder='Required' onChange={handlePasswordChange}/>
                    </div>
                    <div>
                        {verifyPasswordValid ? <label htmlFor="">Repeat Password</label> : <label style={{ color: '#FF6F61' }}>Passwords don't match</label>}
                        <input type="text" placeholder='Required' onChange={handleVerifyPasswordChange}/>
                    </div>
                    <div className="buttonDiv">
                        <input type="button" value="sign up" onClick={handleSubmit} />
                    </div>
                </form>
            </div>
        </>
    )
}

export default RegisterUserScreen