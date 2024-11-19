"use client"
import { ClienteType } from "@/types/type";
import { createContext, useContext, useState } from "react";

type AuthContextProps = {
    user: ClienteType | null;
    login: (user:ClienteType)=>void;
    logout: ()=>void;
}

const AuthContext = createContext<AuthContextProps>({} as AuthContextProps)

const AuthProvider = ({children}:{children:React.ReactNode})=>{

    const [user, setUser] = useState<ClienteType>({
        cep: "",
        idCadastro: 0,
        nome: '',
        email: '',
        senha: ''
    })

    const login = (user: ClienteType)=>{
        setUser(user)
    }

    const logout = ()=>{
        setUser({cep: '',idCadastro: 0, nome: '', email: '', senha: ''})
    }

    return(
        <AuthContext.Provider value={{user, login, logout}}>
            {children}
        </AuthContext.Provider>
    )
}

export {AuthProvider, AuthContext}

//Outra Opção, criando o hook
const useAuth = ()=>{
    const context = useContext(AuthContext)
    return context
}
export {useAuth}