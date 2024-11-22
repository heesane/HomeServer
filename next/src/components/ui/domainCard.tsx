import Image from 'next/image';
import ShortcutButton from "@/components/shortCutButton";
import {domainData} from "@/data/dataTemp";

export default function DomainCard({ name}: { name: string;}) {

  const data:{ name: string, image: string; description: string; url: string } = domainData[name];

  return (
    <div className="flex items-center p-6 space-x-6 text-white rounded-lg">
      {/* 왼쪽 이미지 */}
      <div className="flex-shrink-0">
        <Image
          src= {data.image} // public 폴더 내 이미지 경로
          alt={`${data.name} Image`}
          width={400}
          height={200}
          className="rounded-lg"
        />
      </div>

      {/* 오른쪽 도메인 정보 */}
      <div>
        <div className={"flex flex-col text-sm space-y-2"}>
          <h1 className={"text-2xl font-semibold"}>{data.name}</h1>
          <p className={"text-lg mt-2"}>
            {data.description}
          </p>
        </div>
        <ShortcutButton label={data.name} url={data.url}/>
      </div>
    </div>
  );
}
