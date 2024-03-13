import Link from 'next/link';
import { DarkModeToggle } from './dark-mode-toggle';
export default function Topbar() {
  return (
    <header className="border-b">
        <div className="container px-4 lg:px-6 h-14 flex items-center">
          <Link className="flex items-center justify-center text-2xl" href="/.">
            <span className="font-semibold">Project & Portfolio</span>
          </Link>
          <nav className="ml-auto flex gap-4 sm:gap-6">
            <Link className="text-m font-medium hover:underline underline-offset-4" href="/projects">
              Projects
            </Link>
            <Link className="text-m font-medium hover:underline underline-offset-4" href="/about">
              About Me
            </Link>
            <Link className="text-m font-medium hover:underline underline-offset-4" href="/contacts">
              Contact
            </Link>
          </nav>
          <div className='ml-4'>
          <DarkModeToggle/>
          </div>
        </div>
      </header>
  );
}