import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import { Header } from "@/components/header";
import { Footer } from "@/components/footer";
import React from "react";
import {ThemeToggleButton} from "@/components/themeToggleButton";
import {ThemeProvider} from "@/components/themeContext";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
    title: "ProjectHub",
    description: "ProjectHub is a platform for developers to share insight and discover new projects.",
    icons: {
        icon: "/logo.webp",
    },
    authors: [{name:"Heesang Hwang",url:"https://github.com/heesane"}]
};

export default function RootLayout({
                                       children,
                                   }: Readonly<{
    children: React.ReactNode;
}>) {
    return (
        <html lang="kr">
        <body className={`${inter.className} flex flex-col min-h-screen`}>
        <Header />
        <ThemeProvider>
            <ThemeToggleButton />
        </ThemeProvider>
        <main className="flex-1">
            {children}
        </main>
        <Footer />
        </body>
        </html>
    );
}

