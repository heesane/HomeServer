import Image from 'next/image'
import MainPicture from "./public/images/zzanggu.jpeg"
export default function Portfolio(){
	return (
		<div className = "m-24 flex">
			<Image 
			src={MainPicture} 
			alt="메인 증명사진" 
			className = "flex border-2 border-black h-[176px] w-[117px]" />

			<div className = "flex flex-col ml-8 ">
				<h1 className = "text-4xl font-bold">황희상</h1>
				<p className = "text-2xl font-semibold">백엔드 개발자</p>
				<p>안녕하세요. 저는 백엔드 개발자 황희상입니다.
					백엔드 관련 지식뿐만 아니라 다얀한 개발부분에 대해도 관심이 많습니다.
					
					이러한 경험을 바탕으로 더 나은 서비스를 만들기 위해 노력하고 있습니다.</p>
			</div>
		</div>
	)    
}