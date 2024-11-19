"use client";
import Cabecalho from "@/components/Cabecalho/Cabecalho";
import Footer from "@/components/Footer/Footer";
import { ClienteType } from "@/types/type";

import Link from "next/link";
import { useRouter } from "next/navigation";
import React, { useState } from "react";

export default function Cadastro() {
    const navigate = useRouter();

    const [cliente, setCliente] = useState<ClienteType>({
        idCadastro: 0,
        nome: "",
        email: "",
        senha: "",
        cep: "",
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setCliente({ ...cliente, [name]: value });
    };

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const cabecalho = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(cliente),
        };
        try {
            const response = await fetch(
                "http://localhost:8080/cadastro",
                cabecalho
            );
            console.log(response);
            if (response.ok) {
                alert(`${cliente.nome} cadastrado com sucesso!`);
                setCliente({
                    idCadastro: 0,
                    nome: "",
                    email: "",
                    senha: "",
                    cep: "",
                });
                navigate.push("/");
            } else {
                alert("Erro ao cadastrar!");
            }
        } catch (erro) {
            console.log("Erro ao cadastrar o produto: ", erro);
        }
    };

    const styleInput =
        "relative border-2 border-black/30 rounded-md p-3 text-xl w-[100%] mt-3";
    const styleLabel = "font-roboto text-2xl";
    return (
        <>
            <Cabecalho />
            <div className="max-w-3xl mx-auto">
                <h1 className="font-inter text-5xl mt-6 mb-8 text-center">
                    Cadastre-se
                </h1>

                <form
                    className="border-2 px-8 py-10 rounded mb-14 flex flex-col gap-10"
                    onSubmit={(e) => handleSubmit(e)}
                >
                    <div>
                        <label className={styleLabel} htmlFor="idNome">
                            Nome
                        </label>{" "}
                        <br />
                        <input
                            className={styleInput}
                            type="text"
                            name="nome"
                            id="idNome"
                            value={cliente.nome}
                            onChange={handleChange}
                            placeholder="João Fulano da Silva"
                            required
                        />
                    </div>
                    <div>
                        <label className={styleLabel} htmlFor="idEmail">
                            E-mail
                        </label>{" "}
                        <br />
                        <input
                            className={styleInput}
                            type="email"
                            name="email"
                            id="idEmail"
                            value={cliente.email}
                            onChange={handleChange}
                            placeholder="fulano@exemplo.com.br"
                            required
                        />
                    </div>
                    <div>
                        <label className={styleLabel} htmlFor="idSenha">
                            Senha
                        </label>{" "}
                        <br />
                        <input
                            className={styleInput}
                            type="password"
                            name="senha"
                            id="idSenha"
                            value={cliente.senha}
                            onChange={handleChange}
                            placeholder="Mínimo 1 dígito"
                            required
                        />
                    </div>
                    <div>
                        <label className={styleLabel} htmlFor="idCep">
                            Cep
                        </label>{" "}
                        <br />
                        <input
                            className={styleInput}
                            type="text"
                            name="cep"
                            id="idCep"
                            value={cliente.cep}
                            onChange={handleChange}
                            placeholder="xxxxxxx"
                            required
                        />
                    </div>

                    <button className="button_submit" type="submit">
                        CADASTRAR
                    </button>

                    <Link
                        className="cadastro_links"
                        href={"/cadastro/recuperar"}
                    >
                        Esqueci minha senha
                    </Link>
                </form>
            </div>
            <Footer />
        </>
    );
}
