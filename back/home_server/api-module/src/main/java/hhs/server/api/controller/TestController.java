package hhs.server.api.controller;

import hhs.server.api.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final TestService testService;

    @GetMapping("/grpc/test")
    public String testGRPC() {
        Map<String, Object> result = new HashMap<>();
        String response = testService.Testing("hello");
        result.put("Hello", response);

        // in header
        // Accept-Language: en-US
        // Accept-Language: ko
        Locale currentLocale = LocaleContextHolder.getLocale();
        return response;

    }
}
