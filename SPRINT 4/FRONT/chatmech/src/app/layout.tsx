import type { Metadata } from "next";


import "./globals.css";

import { AuthProvider } from "@/context";

export const metadata: Metadata = {
  title: "ChatMech 2024",
  description: "Idealizado e criado por João Victor Michaeli e Vinícius Saes",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="pt-br">
      <body>
       <AuthProvider>
          {children}
        </AuthProvider>
      </body>
    </html>
  );
}
