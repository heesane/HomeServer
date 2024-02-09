/** @type {import('next').NextConfig} */
const nextConfig = {
    output: "standalone",

    async rewrites() {
        return [
            {
                source: "/:path*",
                destination: "/:path*",

            }
        ]
    }
};

export default nextConfig;
