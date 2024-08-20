import Link from "next/link"
import Image from "next/image"
import {Button} from "@/components/ui/button"
import {Input} from "@/components/ui/input"
import {insightsTemp, projectsTemp} from '@/data/dataTemp'
import FeaturedProjectCard from "@/components/featuredProjectCard";
import InsightsCard from "@/components/insightsCard";

export function Main({apiData}: { apiData: jsonPlaceHolder[] }) {
  return (
    <div className="flex min-h-screen flex-col bg-background">
      <main className="flex-1">
        {/*메인 로고 부분*/}
        <section className="bg-gradient-to-r from-[#5f6caf] to-[#49a09d] py-20 sm:py-32">
          <div className="container flex flex-col items-center gap-8 px-4 sm:px-6 lg:px-8">

            <h1 className="text-center text-4xl font-bold text-white sm:text-5xl lg:text-6xl">
              {`Discover the Best Developer Projects`}
            </h1>

            <p className="max-w-3xl text-center text-lg text-white/80 sm:text-xl">
              {`Explore a curated collection of innovative projects by talented developers and gain valuable
                                insights to
                                inspire your own work.`}
            </p>

            <div className="flex flex-col gap-4 sm:flex-row">
              <Input
                type="search"
                placeholder="Search projects..."
                className="text-black w-full rounded-md bg-white/10 px-4 py-2 placeholder:text-black/60 focus:outline-none sm:w-auto"
                style={{color: 'black'}}
              />
              <Button className="w-full sm:w-auto">
                {`Explore Projects`}
              </Button>
            </div>
          </div>
        </section>

        {/*Featured Projects Sections*/}
        <section className="py-12 sm:py-20">
          <div className="container px-4 sm:px-6 lg:px-8">
            <div className="mb-8 flex items-center justify-between">
              <h2 className="text-2xl font-bold sm:text-3xl">
                {`Featured Projects`}
              </h2>
              <Link href="/explore" className="text-sm font-medium text-primary hover:underline"
                    prefetch={false}>
                {`View All`}
              </Link>
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

        {/*DataType Sections*/}
        <section className="bg-muted py-12 sm:py-20">
          <div className="container px-4 sm:px-6 lg:px-8">
            <div className="mb-8 flex items-center justify-between">
              <h2 className="text-2xl font-bold sm:text-3xl">
                {`Insights`}
              </h2>
              <Link href="/insights" className="text-sm font-medium text-primary hover:underline"
                    prefetch={false}>
                {`View All`}
              </Link>
            </div>
            <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3">
              {
                insightsTemp.map((insight, index) => (
                  <InsightsCard
                    key={index}
                    insight={insight}>
                  </InsightsCard>
                ))}
            </div>
          </div>
        </section>

        {/*About ProjectHub*/}
        <section className="py-12 sm:py-20">
          <div className="container px-4 sm:px-6 lg:px-8">
            <div className="mb-8 flex items-center justify-between">
              <h2 className="text-2xl font-bold sm:text-3xl">
                {`About ProjectHub`}
              </h2>
            </div>
            <div className="grid grid-cols-1 gap-8 sm:grid-cols-2">
              <div>
                <p className="text-m text-muted-foreground">
                  {`ProjectHub is a platform dedicated to showcasing the best developer projects and
                                    sharing valuable
                                    insights from industry experts. Our mission is to inspire and empower developers to
                                    push the
                                    boundaries of what's possible in web development.`}
                </p>
                <br/><br/><br/>
                <p className="mt-4 text-m text-muted-foreground">
                  {`We believe that by sharing knowledge and highlighting innovative projects, we can
                                        foster a vibrant
                                        community of developers who are passionate about creating amazing digital
                                        experiences. Join us on this
                                        journey as we explore the cutting edge of web development and uncover the stories
                                        behind the projects
                                        that are shaping the future.`}
                </p>
              </div>
              <div>
                <Image
                  src="/placeholder.svg"
                  width={640}
                  height={360}
                  alt="About ProjectHub"
                  className="rounded-md"
                  style={{aspectRatio: "640/360", objectFit: "cover"}}
                />
              </div>
            </div>
          </div>
        </section>
      </main>
    </div>
  )
}