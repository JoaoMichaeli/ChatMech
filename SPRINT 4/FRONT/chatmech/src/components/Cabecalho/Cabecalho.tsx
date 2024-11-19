import Image from 'next/image'
import Link from 'next/link'
import React from 'react'
import logo from '@/images/logo_chatmech.png'

export default function Cabecalho() {
  return (
	<header className='bg-[#F8F9FA] flex items-center justify-between drop-shadow-lg'>
		<div className='flex items-center ms-16'>
			<Link href={"/"}><Image src={logo} alt='Logo do chatmech'/></Link>
			<Link href={"/"}><h1 className='text-[#1A7BA0] text-5xl'>ChatMech</h1></Link>
		</div>
		<div className='me-24 text-lg flex space-x-10 items-center'>
			<Link className='font-medium' href={'/login'}>ENTRAR</Link>
			<Link className='bg-blue_1 text-background drop-shadow-sm py-2 px-3 rounded-3xl' href={'/cadastro'}>CADASTRE-SE</Link>
		</div>
	</header>
  )
}
