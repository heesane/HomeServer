import Link from "next/link";
import { MountainIcon } from "lucide-react";

export function Footer() {
    return (
        <footer className="mt-auto bg-muted py-8">
            <div className="container mx-auto flex flex-col items-center justify-between gap-4 px-4 sm:flex-row sm:px-6 lg:px-8">
                <div className="flex items-center gap-2">
                    <MountainIcon className="h-6 w-6" />
                    <span className="text-sm font-medium">ProjectHub</span>
                </div>
                <nav className="flex flex-wrap items-center gap-4 text-sm">
                    <Link href="/explore" className="hover:text-primary" prefetch={false}>
                        Explore
                    </Link>
                    <Link href="/insights" className="hover:text-primary" prefetch={false}>
                        Insights
                    </Link>
                    <Link href="/about" className="hover:text-primary" prefetch={false}>
                        About
                    </Link>
                    <Link href="/contact" className="hover:text-primary" prefetch={false}>
                        Contact
                    </Link>
                    <Link href="/domain" className="hover:text-primary" prefetch={false}>
                        Domain
                    </Link>
                </nav>
                <p className="text-sm text-muted-foreground">&copy; 2024 ProjectHub. All rights reserved.</p>
            </div>
        </footer>
    );
}
