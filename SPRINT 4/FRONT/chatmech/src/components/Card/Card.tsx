import Link from 'next/link'
import React from 'react'

type CardType = {
	titulo: string;
	descricao: string;
	rota: string;
	textoLink: string;
}



export default function Card({titulo, descricao, rota, textoLink}: CardType) {
  return (
	
	<div className='drop-shadow-xl max-w-64 text-center bg-background border rounded-xl p-6 relative bottom-4'>
		<h3 className='font-bebas text-cinza text-xl font-semibold'>{titulo.toUpperCase()}</h3>
		<p className='text-start text-1 text-cinza  leading-loose max-w-[65%] m-auto py-10'>{descricao}</p>
		<Link className='text-blue_1 font-semibold sticky top-[100%]' href={rota} target='_self'>{textoLink}</Link>
	</div>
  )
}
