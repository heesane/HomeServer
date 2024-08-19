import {Card, CardContent, CardFooter, CardHeader} from "@/components/ui/card";
import StarIcon from "@/components/ui/icon/starIcon";
import {Separator} from "@/components/ui/separator";
import {EyeIcon} from "lucide-react";
import Image from "next/image";

export default function FeaturedProjectCard(){
    return (
        <Card>
            <CardHeader>
                <div className="flex items-center gap-4">
                    <Image
                        src="/placeholder.svg"
                        width={48}
                        height={48}
                        alt="Project Logo"
                        className="rounded-full"
                        style={{aspectRatio: "48/48", objectFit: "cover"}}
                    />
                    <div>
                        <h3 className="text-lg font-bold">Project X</h3>
                        <p className="text-sm text-muted-foreground">by John Doe</p>
                    </div>
                </div>
            </CardHeader>
            <CardContent>
                <p className="text-sm text-muted-foreground">
                    A revolutionary new tool for developers to streamline their workflow.
                </p>
            </CardContent>
            <CardFooter>
                <div className="flex items-center gap-2">
                    <StarIcon className="h-4 w-4 text-yellow-500"/>
                    <span className="text-sm text-muted-foreground">4.8</span>
                    <Separator orientation="vertical" className="h-4"/>
                    <EyeIcon className="h-4 w-4 text-muted-foreground"/>
                    <span className="text-sm text-muted-foreground">1.2K</span>
                </div>
            </CardFooter>
        </Card>
    )
}