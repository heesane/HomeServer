import FeaturedProjectCard from "@/components/featuredProjectCard";
import {projectsTemp} from "@/data/dataTemp";
import {Input} from "@/components/ui/input";
import {Button} from "@/components/ui/button";

export default function Explore() {
    return (
        <div className="container py-14 px-4 sm:px-6 lg:px-8 justify-between">
            <div className="text-2xl font-bold sm:text-4xl sm:px-6 lg:px-8 px-4">
                <div className="px-4 sm:px-6 lg:px-8">
                    <h1>
                        Explore
                    </h1>

                    <section className="py-10 sm:py-10">
                        <div className="container">
                            <div className="mb-8 flex items-center justify-between">
                                <h3 className="text-lg font-medium text-primary hover:underline">
                                    Search
                                </h3>
                                <div className="flex">
                                    <Input
                                        type="search"
                                        placeholder="Search projects..."
                                        className="w-full rounded-md bg-white/10 px-4 py-2 text-white placeholder:text-white/60 focus:outline-none sm:w-auto"
                                    />
                                    <Button className="w-full sm:w-auto">
                                        {`Explore Projects`}
                                    </Button>
                                </div>


                                <div className="flex items-center">
                                    <label htmlFor="sort" className="mr-2 text-sm text-muted-foreground">Sort
                                        by:</label>
                                    <select
                                        id="sort"
                                        className="bg-background border border-muted-foreground text-sm rounded-md py-1 px-2 focus:outline-none"
                                    >
                                        <option value="default">Default</option>
                                        <option value="latest">Latest</option>
                                        <option value="stars">Stars</option>
                                        <option value="views">Views</option>
                                        <option value="name">Name</option>
                                    </select>
                                </div>
                            </div>
                            <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3">
                                {/*/반복문으로 Card 동적 생성/*/}
                                {
                                    projectsTemp.map((project, index) => (
                                        <FeaturedProjectCard
                                            key={index}
                                            project={project}>
                                        </FeaturedProjectCard>
                                    ))
                                }
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    );
}