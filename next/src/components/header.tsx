import Link from "next/link";
import {Button} from "@/components/ui/button";
import {MountainIcon} from "lucide-react";

export function Header() {
    return (
        <header className="sticky top-0 z-40 border-b bg-background">
            <div className="container mx-auto flex h-16 items-center justify-between px-4 sm:px-6 lg:px-8">
                <Link href="/" className="flex items-center gap-4 font-bold" prefetch={false}>
                    <MountainIcon className="h-6 w-6"/>
                    <span>{`ProjectHub`}</span>
                </Link>
                <nav className="hidden space-x-4 sm:flex">
                    <Link href="/explore" className="text-sm font-medium hover:text-primary" prefetch={false}>
                        {`Explore`}
                    </Link>
                    <Link href="/insights" className="text-sm font-medium hover:text-primary" prefetch={false}>
                        {`Insights`}
                    </Link>
                    <Link href="/about" className="text-sm font-medium hover:text-primary" prefetch={false}>
                        {`About`}
                    </Link>
                    <Link href="/contact" className="text-sm font-medium hover:text-primary" prefetch={false}>
                        {`Contact`}
                    </Link>
                    <Link href="/domain" className="text-sm font-medium hover:text-primary" prefetch={false}>
                        {`Domain`}
                    </Link>
                </nav>
                <div className="flex items-center gap-4">
                    <Link href="/login" prefetch={false}>
                        <Button variant="outline" className="hidden sm:inline-flex">
                            {`Sign In`}
                        </Button>
                    </Link>
                    <Link href="/registration" prefetch={false}>
                        <Button className="hidden sm:inline-flex">
                            {`Sign Up`}
                        </Button>
                    </Link>
                </div>
            </div>
        </header>
    );
}
