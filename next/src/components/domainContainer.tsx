import DomainCard from "@/components/ui/domainCard";

export default function DomainContainer() {
  return (
    <div className="bg-background p-4 rounded-lg">
      <div className="grid gap-4 mt-4">
        <DomainCard name={"Monitoring"}/>
        <DomainCard name={"Jenkins"}/>
        <DomainCard name={"NginxDashboard"}/>
        <DomainCard name={"KibanaDashboard"}/>
        <DomainCard name={"Portainer"}/>
        <DomainCard name={"KafkaUIDashboard"}/>

      </div>
    </div>
  )
}