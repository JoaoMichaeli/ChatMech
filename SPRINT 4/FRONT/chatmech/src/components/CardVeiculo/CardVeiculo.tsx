import { VeiculoType } from '@/types/type'
import Image from 'next/image'
import React, { useState } from 'react'

import Link from "next/link"
import { FaTrash } from "react-icons/fa";
import { MdModeEditOutline } from "react-icons/md"
import { useRouter } from "next/navigation";

import Modal from '../Modal/Modal';


export default function CardVeiculo({imagem, modelo, quilometragem, ano, placa}:VeiculoType) {

	const navigate = useRouter()

	const [open, setOpen] = useState(false) // abrir e fechar o modal
	const [idDelete, setIdDelete] = useState('') // função de deleção

	const idModal = (placa: string) => {
		setOpen(true) // dar um true pro modal
		setIdDelete(placa)
	}

	const handleDelete = async (placa: string) => {
		try {
			const response = await fetch(`http://localhost:8080/veiculos/${placa}`, {method:"DELETE"})
			if (response.ok) {
				setOpen(false)
				window.location.reload() // recarregar a página
				} else {
					alert("Erro ao deletar veículo")
					setOpen(false)
					navigate.push('/veiculos')
			}
		}
		catch (error){
			console.error("Falha ao apagar registro.", error);
		}
	}

  return (
	<div className='border-2 border-black/20 rounded py-2 px-6'>
		<div className='flex gap-x-10'>
			<Image width={90} height={90} src={imagem} alt='Carro' className='bg-gray-500/20'/>
			<div className="items-center mt-2 flex flex-col gap-y-4">
				<Link title="Editar" href={`/home/veiculos/${placa}`} className="text-blue-600 hover:text-blue-800 transition">
					<MdModeEditOutline className="h-5 w-5" /> {/* Editar */}
				</Link>
				<button title="Excluir" onClick={() => idModal(placa)} 
				className="text-red-600 hover:text-red-800 transition">
					<FaTrash className="h-5 w-5" /> {/* Excluir */}
				</button>
            </div>
		</div>
		<div>
			<h3 className='font-semibold mb-2'>{modelo.toUpperCase()}</h3>
			<p className='font-abeezee'>Placa: {placa}</p>
			<p className='font-abeezee'>Ano: {ano}</p>
			<p className='font-abeezee'>Quilometragem: {quilometragem}</p>
		</div>

		<Modal open={open} onClose={() => setOpen(false)}>
                <div className="text-center w-56">
                    <FaTrash size={56} className="mx-auto text-red-500" />
                    <h3 className="text-lg font-black text-gray-800">Excluir Produto?</h3>
                    <p className="text-gray-500 text-sm">Você tem certeza que deseja excluir o produto?</p>
                </div>
                <div className="flex gap-4">
                    <button className="btn btn-danger w-full" onClick={() => handleDelete(idDelete)}>Excluir</button>
                    <button className="btn btn-light w-full" onClick={() => setOpen(false)}>Cancelar</button>
                </div>
		</Modal>
		
	</div>
  )
}
