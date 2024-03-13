import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import Topbar from "./components/Topbar";
import Lowbar from "./components/Lowbar";
import { ThemeProvider } from "./components/theme-provider";
import { DarkModeToggle } from "./components/dark-mode-toggle";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "Portfolio",
  description: "Portfolio",
  icons: {
      icon: "/favicon.ico",
    },
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className={inter.className}>
        
        <ThemeProvider
          attribute="class"
          defaultTheme="system"
          enableSystem
          disableTransitionOnChange
        >
        <Topbar />
        {children}
        
        <Lowbar/>
        </ThemeProvider>
      </body> 
    </html>
  );
}
