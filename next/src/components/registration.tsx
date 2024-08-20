"use client";
import Link from "next/link";
import {Label} from "@/components/ui/label";
import {Input} from "@/components/ui/input";
import {Button} from "@/components/ui/button";
import CoffeeIcon from "@/components/ui/icon/coffeeIcon";
import ChromeIcon from "@/components/ui/icon/chromeIcon";
import {ChangeEvent, useState} from "react";
import {CheckCircleIcon, XCircleIcon} from "lucide-react";


export function Registration() {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
    confirmPassword: "",
  });

  const [passwordMatch, setPasswordMatch] = useState<boolean | null>(null);

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleConfirmPasswordChange = (e: ChangeEvent<HTMLInputElement>) => {
    const confirmPassword = e.target.value;
    setFormData({
      ...formData,
      confirmPassword: confirmPassword,
    });

    setPasswordMatch(confirmPassword === formData.password);
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    const response = await fetch("/api/signup", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    });

    if (response.ok) {
      alert("Sign Up Successful");
    } else {
      alert("Sign Up Failed");
    }
  };

  return (
    <div className="flex min-h-screen items-center justify-center bg-background px-4 py-12 sm:px-6 lg:px-8">
      <div className="w-full max-w-md space-y-8">
        <div>
          <h2 className="mt-6 text-center text-3xl font-bold tracking-tight text-foreground">
            {`Create an account`}
          </h2>

          <p className="mt-2 text-center text-sm text-muted-foreground">
            Or{" "}
            <Link
              href="/login"
              className="font-medium text-primary hover:text-primary/90"
              prefetch={false}
            >
              sign in
            </Link>
          </p>
        </div>
        <form className="space-y-6" action="/" method="POST" onSubmit={handleSubmit}>
          <div>
            <Label htmlFor="name" className="sr-only">
              Name
            </Label>
            <Input
              id="name"
              name="name"
              type="text"
              autoComplete="name"
              required
              onChange={handleChange}
              className="relative block w-full appearance-none rounded-md border border-input bg-background px-3 py-2 text-foreground placeholder-muted-foreground focus:z-10 focus:border-primary focus:outline-none focus:ring-primary sm:text-sm"
              placeholder="Name"
            />
          </div>
          <div>
            <Label htmlFor="email" className="sr-only">
              {`Email Address`}
            </Label>
            <Input
              id="email"
              name="email"
              type="email"
              autoComplete="email"
              required
              onChange={handleChange}
              className="relative block w-full appearance-none rounded-md border border-input bg-background px-3 py-2 text-foreground placeholder-muted-foreground focus:z-10 focus:border-primary focus:outline-none focus:ring-primary sm:text-sm"
              placeholder="Email address"
            />
          </div>
          <div>
            <Label htmlFor="password" className="sr-only">
              {`Password`}
            </Label>
            <Input
              id="password"
              name="password"
              type="password"
              autoComplete="new-password"
              required
              onChange={handleChange}
              className="relative block w-full appearance-none rounded-md border border-input bg-background px-3 py-2 text-foreground placeholder-muted-foreground focus:z-10 focus:border-primary focus:outline-none focus:ring-primary sm:text-sm"
              placeholder="Password"
            />
          </div>
          <div className="relative">
            <Label htmlFor="confirm-password" className="sr-only">
              {`Confirm Password`}
            </Label>
            <Input
              id="confirm-password"
              name="confirmPassword"
              type="password"
              autoComplete="new-password"
              required
              onChange={handleConfirmPasswordChange}
              className="relative block w-full appearance-none rounded-md border border-input bg-background px-3 py-2 text-foreground placeholder-muted-foreground focus:z-10 focus:border-primary focus:outline-none focus:ring-primary sm:text-sm"
              placeholder="Confirm Password"
            />
            {passwordMatch !== null && (
              <span className="absolute inset-y-0 right-0 flex items-center pr-3">
                {
                  passwordMatch ?
                    (
                      <CheckCircleIcon className="h-5 w-5 text-green-500" aria-hidden="true"/>
                    ) : (
                      <XCircleIcon className="h-5 w-5 text-red-500" aria-hidden="true"/>
                    )}
              </span>
            )}
          </div>
          <div>
            <Button
              type="submit"
              className="relative flex w-full justify-center rounded-md bg-primary py-2 px-4 text-sm font-medium text-primary-foreground shadow-sm hover:bg-primary/90 focus:outline-none focus:ring-2 focus:ring-primary focus:ring-offset-2"
              onClick={() => {
                alert("Sign Up");
                console.log(formData);
              }}
            >
              Sign Up
            </Button>
          </div>
        </form>
        <div className="relative">
          <div className="absolute inset-0 flex items-center">
            <div className="w-full border-t border-muted"/>
          </div>
          <div className="relative flex justify-center text-sm">
            <span className="bg-background px-2 text-muted-foreground">{`Or continue with`}</span>
          </div>
        </div>
        <div className="grid grid-cols-2 gap-4">
          <Button variant="outline" className="flex items-center justify-center">
            <ChromeIcon className="mr-2 h-5 w-5"/>
            {`Google`}
          </Button>
          <Button variant="outline" className="flex items-center justify-center">
            <CoffeeIcon className="mr-2 h-5 w-5"/>
            {`Kakao`}
          </Button>
        </div>
      </div>
    </div>
  );
}
