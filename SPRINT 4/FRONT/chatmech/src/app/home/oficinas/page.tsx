'use client'
import CabecalhoLogin from '@/components/CabecalhoLogin/CabecalhoLogin'
import Footer from '@/components/Footer/Footer'
import { OficinaType } from '@/types/type'
import Image from 'next/image'
import React, { useEffect, useState } from 'react'

export default function Oficinas() {

	const [oficinas, setOficinas] = useState<OficinaType[]>([])

	useEffect(() =>{
		const chamadaApi = async () => {
			const response = await fetch("http://localhost:8080/mecanicas")
			const data = await response.json()
			setOficinas(data)
		}
		chamadaApi()
	}, [])


  return (
	<div>
		<CabecalhoLogin/>
		<div className='flex flex-col items-center'>
			<h1 className='titulo_login'>Oficinas</h1>
			<div className='flex flex-wrap max-w-3xl items-center justify-start gap-x-10 gap-y-4'>
				{oficinas.map((o) =>
					<div key={o.idMecanica} className='max-w-[200px]'>
						<Image  src={o.imagem} width={200} height={200} alt='Logo mecânica'/>
						<p className='font-inter text-xl font-semibold'>{o.nome}</p>
						<p className=''>{o.descricao}</p>
					</div>
				)}
			
			</div>
		</div>
		<Footer/>
	</div>
  )
}
