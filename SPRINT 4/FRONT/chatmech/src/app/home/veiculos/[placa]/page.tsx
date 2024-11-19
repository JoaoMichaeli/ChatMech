"use client"
import CabecalhoLogin from '@/components/CabecalhoLogin/CabecalhoLogin';
import Footer from '@/components/Footer/Footer';
import { VeiculoType } from '@/types/type'
import { useRouter } from 'next/navigation';
import { useState, useEffect } from "react";
import Image from "next/image"



interface EditarVeiculoProps {
    params: Promise< {
        placa: string
    }>;
}
export default function EditarVeiculo({params}:EditarVeiculoProps){
	const navigate = useRouter();
	
	const [placa, setPlaca] = useState<string>("");

	useEffect(() => {
		params.then(p => {
			setPlaca(p.placa);
		});
	}, [params]);
    
	const [veiculo, setVeiculo] = useState<VeiculoType>({
        fabricante: '',
        modelo: '',
        quilometragem: 0,
        ano: 0,
        placa: '',
        imagem: '',
    })

    // Carregar dados do veiculo ao iniciar
    useEffect(() => {
        const chamadaApi = async () => {
            const response = await fetch(`http://localhost:8080/veiculos/${placa}`)
            const data = await response.json()
            setVeiculo(data)
			
        }
        chamadaApi()
    }, [placa])

    //Função para armazenar os dados digitados pelo usuário no obj veiculo
    const handleChange = async (e: React.ChangeEvent<HTMLInputElement>) => {
        const {name, value} = e.target
        setVeiculo({...veiculo,[name]:value})
    }

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault()
		try {
			const response = await fetch(`http://localhost:8080/veiculos/${placa}`,{
                method: 'PUT',
                headers: {'Content-Type':'application/json'},
                body: JSON.stringify(veiculo),
            });
			if (response.ok) {
				alert(`${veiculo.modelo} atualizado com sucesso!`);
				navigate.push('/home/veiculos');
			} else {
				alert("Erro ao atualizar veiculo!");
			}
		} catch (error) {
			console.error("Erro ao atualizar o veiculo", error);
		}
    }

    // Estilos dos elementos
	const spanStyle = "text-blue_1 text-3xl mr-2";
	const inputStyle = "mt-2 h-14 w-[100%] outline-black/60 text-xl font-thin border-2 rounded-sm pl-4";
	const labelStyle = "text-2xl";

    return (
        <div className="flex flex-col min-h-screen">
            <CabecalhoLogin/>
            <div className='flex-grow'>
                <h2 className="titulo_login">Editar Veículo</h2>
                <div className="flex flex-col items-center mb-8">
                    {veiculo.imagem && (
                        <Image width={150} height={150} src={veiculo.imagem} alt='Carro' className='bg-gray-500/20'/>
                    )}
                    <h3 className="text-2xl font-semibold mt-4">{veiculo.modelo}</h3>
                    <p className="text-xl text-gray-500">Fabricante: {veiculo.fabricante}</p>
                </div>

                <form onSubmit={handleSubmit} className="flex flex-col gap-10 font-raleway max-w-[70%] mx-auto h-[100vh]">
                    <div>
                        <label htmlFor="idModelo" className={labelStyle}><span className={`${spanStyle}`}>|</span>Modelo</label><br/>
                        <input className={inputStyle} type="text" name="modelo" id='idModelo' value={veiculo.modelo} placeholder='Digite o modelo do veiculo' onChange={handleChange} required/>
                    </div>
                    <div>
                        <label htmlFor="idFabricante" className={labelStyle}><span className={`${spanStyle}`}>|</span>Fabricante</label><br/>
                        <input className={inputStyle} type="text" name="fabricante" id='idFabricante' value={veiculo.fabricante} placeholder='Digite a fabricante do veiculo' onChange={handleChange} required/>
                    </div>
                    <div>
                        <label htmlFor="idAno" className={labelStyle}><span className={`${spanStyle}`}>|</span>Ano</label><br/>
                        <input className={inputStyle} type="number" name="ano" id='idAno' value={veiculo.ano} placeholder='Digite o ano do veiculo' onChange={handleChange} required/>
                    </div>
                    <div>
                        <label htmlFor="idQuilometragem" className={labelStyle}><span className={`${spanStyle}`}>|</span>Quilometragem</label><br/>
                        <input className={inputStyle} type="number" name="quilometragem" id='idQuilometragem' value={veiculo.quilometragem} placeholder='Digite a quilometragem do veiculo' onChange={handleChange} required/>
                    </div>
                    <div>
                    <label htmlFor="idPlaca" className={labelStyle}><span className={`${spanStyle}`}>|</span>Placa</label><br/>
                    <input className={inputStyle} type="text" name="placa" id='idPlaca' value={veiculo.placa} placeholder='Digite a placa do veiculo' onChange={handleChange} required/>
                    </div>

                    <div className="text-center">
						<button type='submit' className="button_submit">Editar Veiculo</button>
					</div>
                </form>
            </div>
            <Footer/>
        </div>
    )
}