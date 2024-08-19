import {Card, CardContent, CardFooter, CardHeader} from "@/components/ui/card";
import StarIcon from "@/components/ui/icon/starIcon";
import {Separator} from "@/components/ui/separator";
import {EyeIcon} from "lucide-react";
import Image from "next/image";

export default function FeaturedProjectCard({project}: { project:Project }) {
    return (
        <Card>
            <CardHeader>
                <div className="flex items-center gap-4">
                    <Image
                        src={project.logo}
                        width={48}
                        height={48}
                        alt="Project Logo"
                        className="rounded-full"
                        style={{aspectRatio: "48/48", objectFit: "cover"}}
                    />
                    <div>
                        <h3 className="text-lg font-bold">{project.name}</h3>
                        <p className="text-sm text-muted-foreground">by {project.author}</p>
                    </div>
                </div>
            </CardHeader>
            <CardContent>
                <p className="text-sm text-muted-foreground">
                    {project.description}
                </p>
            </CardContent>
            <CardFooter>
                <div className="flex items-center gap-2">
                    <StarIcon className="h-4 w-4 text-yellow-500"/>
                    <span className="text-sm text-muted-foreground">{project.stars}</span>
                    <Separator orientation="vertical" className="h-4"/>
                    <EyeIcon className="h-4 w-4 text-muted-foreground"/>
                    <span className="text-sm text-muted-foreground">{project.views}</span>
                </div>
            </CardFooter>
        </Card>
    )
}