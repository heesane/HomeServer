export const insightsTemp = [
    {
        id: 1,
        thumbnail: "https://avatars.githubusercontent.com/u/10639145?v=4",
        title: "small project",
        description: "this is small project",
        date: "2021-08-06",
        readingTime: "5 min read"

    },
    {
        id: 2,
        thumbnail: "https://avatars.githubusercontent.com/u/10639145?v=4",
        title: "small2 project",
        description: "this is small project",
        date: "2021-11-06",
        readingTime: "15 min read"
    },
    {
        id: 3,
        thumbnail: "https://avatars.githubusercontent.com/u/10639145?v=4",
        title: "small3 project",
        description: "this is small project",
        date: "2021-12-06",
        readingTime: "25 min read"
    },
];

export const projectsTemp = [
    {
        id:1,
        logo: "https://avatars.githubusercontent.com/u/10639145?v=4",
        name: "small project",
        author: "heesang",
        description: "this is small project",
        stars: 300,
        views: "1000"

    },
    {
        id:2,
        logo: "https://avatars.githubusercontent.com/u/10639145?v=4",
        name: "small project2",
        author: "heesang2",
        description: "this is small project2",
        stars: 400,
        views: "1000"

    },
    {
        id:3,
        logo: "https://avatars.githubusercontent.com/u/10639145?v=4",
        name: "small project3",
        author: "heesang3",
        description: "this is small project3",
        stars: 500,
        views: "1000"

    },
];

export const domainData: Record<string, { name: string,image: string; description: string; url: string }> = {
    "Monitoring": {
        name: "Monitoring",
        image: "/png/monitoring.png",
        description: "This domain provides monitoring services for servers and infrastructure, helping you maintain system health.",
        url: "https://monitoring.coded-by.me",
    },
    "Jenkins": {
        name: "Jenkins",
        image: "/png/jenkins.png",
        description: "Jenkins is an open-source automation server that helps automate parts of software development related to building, testing, and deploying.",
        url: "https://jenkins.coded-by.me",
    },
    "NginxDashboard": {
        name: "Nginx Proxy Manager",
        image: "/png/nginx-dashboard.png",
        description: "Nginx Dashboard provides a web interface to manage Nginx configurations and monitor server performance.",
        url: "https://manager.coded-by.me",
    },
    "KibanaDashboard": {
        name: "Kibana",
        image: "/png/kibana.png",
        description: "Kibana Dashboard is an open-source data visualization plugin for Elasticsearch. It provides visualization capabilities on top of the content indexed on an Elasticsearch cluster.",
        url: "https://kibana.coded-by.me",
    },
    "Portainer": {
        name: "Portainer",
        image: "/png/portainer.png",
        description: "Portainer is an open-source tool for managing containerized applications. It provides a user-friendly interface to manage Docker containers, images, networks, and volumes.",
        url: "https://portainer.coded-by.me",
    },
    "KafkaUIDashboard": {
        name: "Kafka UI",
        image: "/png/kafka-ui.png",
        description: "Kafka UI Dashboard is a web-based tool for monitoring and managing Apache Kafka clusters. It provides a user-friendly interface to view topics, partitions, and consumer groups.",
        url: "https://kafka.coded-by.me",
    },
};