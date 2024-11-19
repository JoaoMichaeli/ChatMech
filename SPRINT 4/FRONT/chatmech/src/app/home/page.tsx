"use client"
import React from "react";
import CabecalhoLogin from "@/components/CabecalhoLogin/CabecalhoLogin";
import Footer from "@/components/Footer/Footer";
import BannerHome from "@/components/BannerHome/BannerHome";
import banner_img1 from "@/images/banner_home1.png";
import banner_img2 from "@/images/banner_home2.png"
import Image from "next/image";
import logo_lg from '@/images/logo_chatmech_lg.png'

export default function Home() {

	

    const banner1_info = {
        imgSrc: banner_img1,
        alt: "Imagem de dois carros pratas",
		estilo: '',
		tituloCard: 'Veiculos',
		textoCard: 'Acesse seus veículos cadastrados',
		rota : '/home/veiculos'
    };
	const banner2_info = {
        imgSrc: banner_img2,
        alt: "Imagem de uma mecânica mexendo no motor de um carro",
		estilo: '',
		tituloCard: 'Oficinas',
		textoCard: 'Veja as oficinas cadastradas',
		rota : '/home/oficinas'
    };

    return (
        <>
            <CabecalhoLogin />
            <h1 className="titulo_login">Área do cliente</h1>
            <BannerHome
                imgSrc={banner1_info.imgSrc}
                alt={banner1_info.alt}
				
				tituloCard={banner1_info.tituloCard}
				textoCard={banner1_info.textoCard}
				rota={banner1_info.rota}
            />
			<section className="flex justify-between items-center px-48 font-roboto lg:px-16">
				<div>
					<h2 className="text-5xl font-bold mb-3 leading-relaxed lg:text-4xl">Converse com o Mechzinho</h2>
					<p className="text-3xl text-black/30 mb-4 lg:text-2xl">Conheça nosso assistente virtual!</p>
					<button className="button_submit max-w-[200px]">Conversar</button>
				</div>
				<div>
					<Image src={logo_lg} alt="Logo do chatmech"/>
				</div>
			</section>
			<BannerHome imgSrc={banner2_info.imgSrc} alt={banner2_info.alt}  tituloCard={banner2_info.tituloCard} textoCard={banner2_info.textoCard} rota={banner2_info.rota}/>
			<div className="mb-20"></div>
            <Footer />
        </>
    );
}
