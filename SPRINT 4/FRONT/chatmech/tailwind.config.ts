import type { Config } from "tailwindcss";


const config: Config = {
  content: [
    "./src/pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/components/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  theme: {
    extend: {
      fontFamily: {
        roboto: ['Roboto', 'sans-serif'],
        bebas: ['Bebas Neue', 'sans-serif'],
        inter: ['Inter', 'sans-serif'],
        abeezee: ['ABeeZee', 'sans-serif'],
      },
      colors: {
        background: "#F8F9FA",
        blue_1: "#1A7BA0",
        blue_2: "#0C4054",
        blue_3: "#4CC5F4",
        cinza: "#565555"
      },
    },
  },
  plugins: [],
};
export default config;
