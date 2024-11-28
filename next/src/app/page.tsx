import {Main} from "@/components/main";
import {fetchers} from "@/utils/fetchers";
import {JsonPlaceHolder} from "@/type/interface";

export default async function Home() {
  const rawData = await fetchers("https://jsonplaceholder.typicode.com/posts", { method: "GET" });
  const apiData:JsonPlaceHolder[] = rawData.map((data: any) => {
    return {
      userId: data.userId,
      id: data.id,
      title: data.title,
      body: data.body
    };
  });
  return (
    <Main apiData={apiData}/>
  );
}
