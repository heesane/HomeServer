// Date 타입 지정

// Insights 타입 지정
type Insights = {
    id: number;
    thumbnail: string;
    title: string;
    description: string;
    date: string;
    readingTime: string;
}

// Project 타입 지정
type Project = {
    id: number;
    logo: string;
    name: string;
    author: string;
    description: string;
    stars: number;
    views: string;
}

// Test용 JSON
type jsonPlaceHolder = {
    userId: number;
    id: number;
    title: string;
    body: string;
}

// Theme용 Type
type Theme = "light" | "dark";

// 회원가입시 필요한 Type
type RegistrationData = {
    name: string;
    email: string;
    password: string;
}

// 회원가입 시 반환 Type
type RegistrationResponse = {
    status: string;
    message: string;
}