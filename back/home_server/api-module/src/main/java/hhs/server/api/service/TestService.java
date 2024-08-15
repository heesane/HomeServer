package hhs.server.api.service;


import hhs.server.api.helloworld.GreetingReq;
import hhs.server.api.helloworld.GreetingRes;
import hhs.server.api.helloworld.TestGrpc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestService {

    @GrpcClient("test")
    private TestGrpc.TestBlockingStub helloServiceBlockingStub;

    public String Testing(String string) {
        try{
            log.info("Testing gRPC");
            log.info("Request: " + string);
            GreetingRes response = this.helloServiceBlockingStub.greeting(
                    GreetingReq.newBuilder()
                            .setSome(string)
                            .build()
            );
            String result = response.toString();
            log.info("Response: " + result);
            return result;
        }catch(Exception e){
            return e.getMessage();
        }
    }
}
