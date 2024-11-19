import Image, { StaticImageData } from 'next/image'
import Link from 'next/link';
import React from 'react'


type BannerHomeTypes = {
	imgSrc: StaticImageData;
	alt: string;
	tituloCard: string;
	textoCard: string;
	rota: string;
}

export default function BannerHome({imgSrc, alt, tituloCard, textoCard, rota}:BannerHomeTypes) {
  return (
	<section className={`bg-blue_2 flex justify-center font-roboto py-12`}>
		<div className='flex'>
			<div className='bg-white max-h-fit max-w-[380px] rounded py-8 px-6 z-20'>
				<p className='text-blue_1 font-semibold text-xl mb-2'>{tituloCard}</p>
				<p className='font-bold text-3xl mb-6'>{textoCard}</p>
				<div className='text-center'><Link href={rota}><button className='button_submit relative max-w-[50%]'>Acessar</button></Link></div>
			</div>
			<Image className='mt-8 z-10' src={imgSrc} alt={alt}/>
		</div>
	</section>
  )
}


//className='bg-white max-h-fit max-w-[380px] rounded py-8 px-6  absolute left-[30%]'
//className=' ml-[43%] mt-8'