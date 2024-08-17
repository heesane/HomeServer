import Link from "next/link";
import {Button} from "@/components/ui/button";
import {Sheet, SheetContent, SheetTrigger} from "@/components/ui/sheet";
import {MenuIcon, MountainIcon} from "lucide-react";

export function Header() {
    return (
        <header className="sticky top-0 z-40 border-b bg-background">
            <div className="container flex h-16 items-center justify-between px-4 sm:px-6 lg:px-8">
                <Link href="/" className="flex items-center gap-2 font-bold" prefetch={false}>
                    <MountainIcon className="h-6 w-6"/>
                    <span>ProjectHub</span>
                </Link>
                <nav className="hidden space-x-4 sm:flex">
                    <Link href="/explore" className="text-sm font-medium hover:text-primary" prefetch={false}>
                        Explore
                    </Link>
                    <Link href="/insights" className="text-sm font-medium hover:text-primary" prefetch={false}>
                        Insights
                    </Link>
                    <Link href="/about" className="text-sm font-medium hover:text-primary" prefetch={false}>
                        About
                    </Link>
                    <Link href="/contact" className="text-sm font-medium hover:text-primary" prefetch={false}>
                        Contact
                    </Link>
                </nav>
                <div className="flex items-center gap-4">
                    <Link href="/login" prefetch={false}>
                        <Button variant="outline" className="hidden sm:inline-flex">
                            Sign In
                        </Button>
                    </Link>
                    <Link href="/registration" prefetch={false}>
                        <Button className="hidden sm:inline-flex">
                            Sign Up
                        </Button>
                    </Link>
                    <Sheet>
                        <SheetTrigger asChild>
                            <Button variant="outline" size="icon" className="sm:hidden">
                                <MenuIcon className="h-6 w-6"/>
                                <span className="sr-only">Toggle Menu</span>
                            </Button>
                        </SheetTrigger>
                        <SheetContent side="right" className="sm:hidden">
                            <nav className="grid gap-4 p-4 text-lg font-medium">
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
                                <div className="flex flex-col gap-2">
                                    <Link href="/login" prefetch={false}>
                                        <Button variant="outline">Sign In</Button>
                                    </Link>
                                    <Link href="/registration" prefetch={false}>
                                        <Button>Sign Up</Button>
                                    </Link>
                                </div>
                            </nav>
                        </SheetContent>
                    </Sheet>
                </div>
            </div>
        </header>
    );
}
