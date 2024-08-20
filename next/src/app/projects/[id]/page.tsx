import { ProjectId } from '@/type/interface'


export default function Home({params}: {params: ProjectId}) {
  return (
    <div>
      <h1>
        {params.id}
      </h1>
    </div>
  )
}