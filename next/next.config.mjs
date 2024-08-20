/** @type {import('next').NextConfig} */
const nextConfig = {
    images:{
        remotePatterns:[
            {
                protocol: 'https',
                hostname: 'avatars.githubusercontent.com',
                port: '',
                pathname:'/**'
            },
            {
                protocol: 'http',
                hostname: 'avatars.githubusercontent.com',
                port: '',
                pathname:'/**'
            },
        ],
    },
    // async rewrites() {
    //     return [
    //         {
    //             source: "/:path*",
    //             destination: "(전달받은 API 주소)/:path*",
    //         }
    //     ]
    // }
};

export default nextConfig;
