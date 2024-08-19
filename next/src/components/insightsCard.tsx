import {Card, CardContent, CardFooter, CardHeader} from "@/components/ui/card";
import Image from "next/image";
import {CalendarIcon, ClockIcon} from "lucide-react";
import {Separator} from "@/components/ui/separator";

export default function InsightsCard({insight}:{insight:Insights}){
    return (
        <Card>
            <CardHeader>
                <Image
                    src={insight.thumbnail}
                    width={640}
                    height={360}
                    alt="DateType Image"
                    className="rounded-t-md"
                    style={{aspectRatio: "640/360", objectFit: "cover"}}
                />
            </CardHeader>
            <CardContent>
                <h3 className="text-lg font-bold">
                    {insight.title}
                </h3>
                <p className="mt-2 text-sm text-muted-foreground">
                    {insight.description}
                </p>
            </CardContent>
            <CardFooter>
                <div className="flex items-center gap-2">
                    <CalendarIcon className="h-4 w-4 text-muted-foreground"/>
                    <span className="text-sm text-muted-foreground">
                        {insight.date}
                    </span>
                    <Separator orientation="vertical" className="h-4"/>
                    <ClockIcon className="h-4 w-4 text-muted-foreground"/>
                    <span className="text-sm text-muted-foreground">
                        {insight.readingTime}
                    </span>
                </div>
            </CardFooter>
        </Card>
    )
}