import { useState, useEffect } from "react"

const UseFetch = () => {
    const [data, setData] = useState(null)
    const [config, setConfig] = useState(null)
    const [method, setMethod] = useState(null)
    const [loading, setLoading] = useState(false)
    const [id, setId] = useState(null)
    const [url, setUrl] = useState('')

    console.log("Passei no fetch")

    const httpConfig = (method, data = null, id = null, token = null) => {
        if (method === "POST" || method === "PUT") {
            setConfig({
                method,
                headers: {
                    "Content-Type": "application/json",
                    ...(token && { "Authorization": `Bearer ${token}` }),
                },
                body: JSON.stringify(data),
            })
            setMethod(method)
            if (method === "PUT") setId(id)
        } else if (method === "DELETE") {
            setConfig({
                method,
                headers: {
                    ...(token && { "Authorization": `Bearer ${token}` }),
                },
            })
            setMethod(method)
            setId(id)
        } else if (method === "GET") {
            if (token) {
                setConfig({
                    method,
                    headers: {
                        "Authorization": `Bearer ${token}`,
                    },
                })
            } else {
                setConfig(null)
            }
            setMethod(method)
        }
    }

    useEffect(() => {
        const httpRequest = async () => {
            setLoading(true)
            try {
                let res
                if (config) {
                    res = await fetch(url, config)
                } else {
                    res = await fetch(url)
                }

                const json = await res.json()
                console.log(json)
                setData(json)
            } catch (error) {
                console.error("Error fetching data:", error)
            } finally {
                setLoading(false)
            }
        }

        if (method === "GET") {
            httpRequest()
        }
    }, [method, config, url])

    useEffect(() => {
        const httpRequest = async () => {
            if (!method || method === "GET") return

            setLoading(true)
            try {
                let res
                if (method === "POST") {
                    res = await fetch(url, config)
                } else if (method === "PUT" || method === "DELETE") {
                    res = await fetch(`${url}/${id}`, config)
                }

                if (!res.ok) {
                    throw new Error(`ERROR ON FETCH URL OR CONFIG: ${res.status}.`)
                }

                const json = await res.json()
                setData(json)
            } catch (error) {
                console.error("Error on requisition:", error.message)
            } finally {
                setLoading(false)
                setMethod(null)
            }
        }

        httpRequest()
    }, [method, config, url, id])

    return { httpConfig, data, loading, setId, setUrl, url }
}

export default UseFetch
