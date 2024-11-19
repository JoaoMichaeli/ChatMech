"use client"
import CabecalhoLogin from '@/components/CabecalhoLogin/CabecalhoLogin'
import CardVeiculo from '@/components/CardVeiculo/CardVeiculo'
import Footer from '@/components/Footer/Footer'
import { VeiculoType } from '@/types/type'
import React, { useEffect, useState } from 'react'

export default function Veiculos() {

	const [veiculos, setVeiculos] = useState<VeiculoType[]>([])

	useEffect(() => {
	  const chamadaApi = async () => {
		const response = await fetch("http://localhost:8080/veiculos");
		const data = await response.json()
		setVeiculos(data)
	  }
	  chamadaApi()
	}, [])
	
	
	return (
	<div>
		<CabecalhoLogin/>
		<div className='flex flex-col font-roboto items-center justify-center h-[80vh] max-w-[900px] mx-auto'>
			<h2 className='mt-3 text-3xl font-inter font-semibold'>Veículos Cadastrados</h2>
			<div>
				<div className='flex flex-wrap justify-center items-center gap-x-14 max-w-[40%] xl:max-w-fit gap-y-14 mt-10'>
					{veiculos.map((v) =>
					<CardVeiculo key={v.placa} imagem={v.imagem} fabricante={v.fabricante} modelo={v.modelo} placa={v.placa} quilometragem={v.quilometragem} ano={v.ano}/>
					)}
				</div>
			</div>
			
		</div>
		<Footer/>
	</div>
  )
}
