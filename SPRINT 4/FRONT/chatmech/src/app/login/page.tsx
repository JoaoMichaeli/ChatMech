"use client";
import Link from "next/link";

import "../globals.css";
import { LoginType} from "@/types/type";
import { useRouter } from "next/navigation";
import Cabecalho from "@/components/Cabecalho/Cabecalho";
import Footer from "@/components/Footer/Footer";

import { useState } from "react";

export default function Login() {
    const navigate = useRouter();

    

    const [dados, setDados] = useState<LoginType>({
        email: "",
        senha: "",
    });


    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setDados({
            ...dados,
            [name]: value,
        });
    };

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        try {
            const response = await fetch(
                `http://localhost:8080/cadastro/login/${dados.email}/${dados.senha}`
            );
            if (response.ok) {
				const usuario = await response.json();
                // AQUELA TRETA GOSTOSA QUE VIMOS NA AULA ONLINE
                const teste = usuario
                setDados(teste);
                
                // ---------------------------------------------
                navigate.push(`/home`);
            } else if (response.status == 400 || response.status == 404) {
                alert("Email ou Senha Inválidos!");
                navigate.push("/login");
            }
			else {
                alert("ERRO AO FAZER O LOGIN! Tente novamente.");
                navigate.push("/login");
            }
        } catch (error) {
            alert(`Erro ao fazer o login: ${error}`);
            navigate.push("/login");
        }
    };

    const styleLabel = "font-roboto text-2xl";
    const styleInput =
        "relative border-2 border-black/20 rounded-xl p-3 text-xl w-[100%] mt-3";
    return (
		<>
		<Cabecalho/>
        <div className="max-w-xl mx-auto">
            <h1 className="font-inter text-5xl mt-6 mb-8 text-center">
                Login
            </h1>
            <form
                className="border-2 px-8 py-10 rounded-md mb-14 flex flex-col gap-10"
                onSubmit={(e) => handleSubmit(e)}
            >
                <div>
                    <label htmlFor="idEmail" className={styleLabel}>
                        Endereço de e-mail
                    </label>
                    <input
                        className={styleInput}
                        type="email"
                        name="email"
                        id="idEmail"
                        onChange={(e) => handleChange(e)}
                    />
                </div>
                <div>
                    <label htmlFor="idSenha" className={styleLabel}>
                        Senha
                    </label>
                    <input
                        className={styleInput}
                        type="password"
                        name="senha"
                        id="idSenha"
                        onChange={(e) => handleChange(e)}
                    />
                </div>
                <button type="submit" className="button_submit">
                    ENTRAR
                </button>
                <Link className="cadastro_links" href={"/cadastro/recuperar"}>
                    Esqueci minha senha
                </Link>
                <Link className="cadastro_links" href={"/cadastro"}>
                    Não tem conta? Cadastre-se aqui
                </Link>
            </form>
        </div>
		<Footer/>
		</>
    );
}
