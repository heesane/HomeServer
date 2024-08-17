import Link from "next/link";
import { Label } from "@/components/ui/label";
import { Input } from "@/components/ui/input";
import { Checkbox } from "@/components/ui/checkbox";
import { Button } from "@/components/ui/button";
import ChromeIcon from "@/components/ui/icon/chromeIcon";
import CoffeeIcon from "@/components/ui/icon/coffeeIcon";

export function Login() {
    return (
        <div className="flex min-h-screen items-center justify-center bg-background px-4 py-12 sm:px-6 lg:px-8">
            <div className="w-full max-w-md space-y-8">
                <div>
                    <h2 className="mt-6 text-center text-3xl font-bold tracking-tight text-foreground">
                        Sign in to your account
                    </h2>
                    <p className="mt-2 text-center text-sm text-muted-foreground">
                        Or{" "}
                        <Link
                            href="/registration"
                            className="font-medium text-primary hover:text-primary/90"
                            prefetch={false}
                        >
                            sign up
                        </Link>
                    </p>
                </div>
                <form className="space-y-6" action="#" method="POST">
                    <div>
                        <Label htmlFor="email" className="sr-only">
                            Email address
                        </Label>
                        <Input
                            id="email"
                            name="email"
                            type="email"
                            autoComplete="email"
                            required
                            className="relative block w-full appearance-none rounded-md border border-input bg-background px-3 py-2 text-foreground placeholder-muted-foreground focus:z-10 focus:border-primary focus:outline-none focus:ring-primary sm:text-sm"
                            placeholder="Email address"
                        />
                    </div>
                    <div>
                        <Label htmlFor="password" className="sr-only">
                            Password
                        </Label>
                        <Input
                            id="password"
                            name="password"
                            type="password"
                            autoComplete="current-password"
                            required
                            className="relative block w-full appearance-none rounded-md border border-input bg-background px-3 py-2 text-foreground placeholder-muted-foreground focus:z-10 focus:border-primary focus:outline-none focus:ring-primary sm:text-sm"
                            placeholder="Password"
                        />
                    </div>
                    <div className="flex items-center justify-between">
                        <div className="flex items-center">
                            <Checkbox
                                id="remember-me"
                                name="remember-me"
                                className="h-4 w-4 rounded text-primary focus:ring-primary"
                            />
                            <Label htmlFor="remember-me" className="ml-2 block text-sm text-muted-foreground">
                                Remember me
                            </Label>
                        </div>
                        <div className="text-sm">
                            <Link href="#" className="font-medium text-primary hover:text-primary/90" prefetch={false}>
                                Forgot your password?
                            </Link>
                        </div>
                    </div>
                    <div>
                        <Button
                            type="submit"
                            className="relative flex w-full justify-center rounded-md bg-primary py-2 px-4 text-sm font-medium text-primary-foreground shadow-sm hover:bg-primary/90 focus:outline-none focus:ring-2 focus:ring-primary focus:ring-offset-2"
                        >
                            Sign in
                        </Button>
                    </div>
                </form>
                <div className="relative">
                    <div className="absolute inset-0 flex items-center">
                        <div className="w-full border-t border-muted"/>
                    </div>
                    <div className="relative flex justify-center text-sm">
                        <span className="bg-background px-2 text-muted-foreground">Or continue with</span>
                    </div>
                </div>
                <div className="grid grid-cols-2 gap-4">
                    <Button variant="outline" className="flex items-center justify-center">
                        <ChromeIcon className="mr-2 h-5 w-5"/>
                        Google
                    </Button>
                    <Button variant="outline" className="flex items-center justify-center">
                        <CoffeeIcon className="mr-2 h-5 w-5"/>
                        Kakao
                    </Button>
                </div>
            </div>
        </div>
    );
}
