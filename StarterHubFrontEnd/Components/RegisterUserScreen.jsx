import { useState } from 'react'
import './RegisterUserScreen.css'

const RegisterUserScreen = () => {
    
    const [username, setUsername] = useState('')
    const [usernameValid, setUsernameValid] = useState(true)

    const [email, setEmail] = useState(null)
    const [emailValid, setEmailValid] = useState(true)

    const [phoneNumber, setPhoneNumber] = useState('')
    const [phoneNumberValid, setPhoneNumberValid] = useState(true)

    const [password, setPassword] = useState('')
    const [passwordValid, setPasswordValid] = useState(true)

    const [verifyPassword, setVerifyPassword] = useState('')

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
        const passwordRegex = /^[a-zA-Z][a-zA-Z0-9.-_]{3,24}$/

        if(usernameRegex.test(username) && emailRegex.test(email) && phoneNumber.length === 15 && passwordRegex.test(password && passwordValid.length > 0)){
            if(password === verifyPassword){
                const newUser = {
                    username: username,
                    password: password,
                    email: email,
                    phone: phoneNumber 
                }

                console.log(newUser)
            } else{
                console.log("SENHA INVÁLIDA")
                console.log(password.length)
                console.log(verifyPassword.length)
            }
        } else{
            console.log("formato invalido")
        }
    }
    
    return (
        <>
            <div className="container">
                <form className="registerContainer">
                    <div>
                        <label>Username</label>
                        <input type="text" placeholder='Required' onChange={handleUsernameChange}/>
                    </div>
                    <div>
                        <label htmlFor="">Email</label>
                        <input type="email" placeholder='Required' onChange={handleEmailChange}/>
                    </div>
                    <div>
                        <label htmlFor="">Phone number</label>
                        <input type="text" placeholder='Required' value={phoneNumber} onChange={handlePhoneNumberChange}/>
                    </div>
                    <div>
                        <label htmlFor="">Password</label>
                        <input type="text" placeholder='Required' onChange={handlePasswordChange}/>
                    </div>
                    <div>
                        <label htmlFor="">Repeat Password</label>
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