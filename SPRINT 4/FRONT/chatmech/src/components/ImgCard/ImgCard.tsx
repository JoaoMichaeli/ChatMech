import React from 'react'
import Image, { StaticImageData } from 'next/image';
type ImgCardType = {
	imgSrc: StaticImageData;
};

export default function ImgCard({imgSrc}:ImgCardType) {
  return (
	<>
		<Image  src={imgSrc} alt='Pessoa legal' />	
	</>
  );
}
