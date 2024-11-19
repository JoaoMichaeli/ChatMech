
import Image from 'next/image'
import Link from 'next/link'
import React from 'react'
import logo from '@/images/logo_chatmech.png'

export default function CabecalhoLogin() {
  return (
	<header className='bg-[#F8F9FA] flex items-center justify-between drop-shadow-lg'>
		<div className='flex items-center ms-16'>
			<Link href={"/home"}><Image src={logo} alt='Logo do chatmech'/></Link>
			<Link href={"/home"}><h1 className='text-[#1A7BA0] text-5xl'>ChatMech</h1></Link>
		</div>
		<div className='me-24 text-lg flex space-x-10 items-center'>
			<Link className='home_nav' href={'/home/quem-somos'}>Quem somos</Link>
			<Link className='home_nav' href={'/home/veiculos'}>Veículos</Link>
			<Link className='home_nav' href={'/home/oficinas'}>Oficinas</Link>
			<Link className='home_nav' href={'/home'}>Área do cliente</Link>
		</div>
	</header>
  )
}