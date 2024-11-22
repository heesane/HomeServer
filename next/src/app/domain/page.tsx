import DomainContainer from "@/components/domainContainer";

export default function Domain(){
  return (
    <div className="container mx-auto py-14 px-4 sm:px-6 lg:px-8 justify-between">
      <div className="text-2xl font-bold sm:text-4xl sm:px-6 lg:px-8 px-4 justify-between">
        <div className="px-4 sm:px-6 lg:px-8">
          <h1>Domain</h1>

          <DomainContainer/>
        </div>

      </div>
    </div>
  );
}