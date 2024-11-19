import { StaticImageData } from "next/image";

export type ClienteType = {
	idCadastro: number,
	nome: string,
	email: string,
	senha: string,
	cep: string
}

export type LoginType = {
	email: string,
	senha: string,
}

export type VeiculoType = {
	fabricante: string,
	modelo: string,
	quilometragem: number,
	ano: number,
	placa: string,
	imagem: string | StaticImageData,
}

export type ModalProps = {
    open: boolean;
    onClose: () => void;
    children: React.ReactNode;
}

export type OficinaType = {
	nome: string,
	imagem: string,
	descricao: string,
	idMecanica: number,
}