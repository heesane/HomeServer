import Image from "next/image";
import Lowbar from "@/app/components/Lowbar";
import Topbar from "@/app/components/Topbar";
import { DarkModeToggle } from "./components/dark-mode-toggle";

export default function Home() {
  return (
    <div>
      <DarkModeToggle />
    </div>
  );
}