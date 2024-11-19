"use client"
import Image from 'next/image'
import React from 'react'
import Link from 'next/link'

import telefone from '@/images/icon-tel.png'
import github from '@/images/Github.png'
import linkedIn from '@/images/Linkedin.png'
import figma from '@/images/Figma.png'

export default function Footer() {
  return (
	<footer className='border-t-2 border-gray-400 sticky top-[100vh] p-3 flex justify-between'>
		<div className='flex items-start space-x-4  ms-16'>
			<Image className='mt-2' src={telefone} alt='Ícone de um telefone'/>
			<div>
				<h2 className='text-blue_1 font-semibold text-lg'><Link href={'/'}>SUPORTE</Link></h2>
				<p className='max-w-64 text-base'>Caso possua algum problema ao acessar o ChatMech entre em contato com nosso Suporte.</p>
			</div>
		</div>
		<div className='flex items-center space-x-6 me-24'>
			
			<a href="https://github.com/ViniciuSaeSouza/chatmech" target='_blank'>
				<Image src={github} alt="Ícone do Github"/>
			</a>
			<a href="" target='_blank'>
				<Image src={linkedIn} alt="Ícone do LinkedIn"/>
			</a>
			<a href="https://www.figma.com/design/pznipVE6pPxblE4Cfz9DmM/Sprint-4?node-id=0-1&t=fWElpruZEVM5cf0P-1" target='_blank'>
				<Image src={figma} alt="Ícone do Figma"/>
			</a>
		</div>
	</footer>
  )
}
