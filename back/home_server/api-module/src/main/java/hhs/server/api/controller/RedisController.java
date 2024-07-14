package hhs.server.api.controller;

import hhs.server.api.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
    private final RedisService redisService;

    @Autowired
    public RedisController(RedisService redisService){
        this.redisService = redisService;
    }

    @PostMapping("/redis/save")
    public String saveData(@RequestParam String key, @RequestParam String value){
        redisService.saveData(key, value);
        return "success";
    }

    @GetMapping("/redis/show")
    public String showData(@RequestParam String key){
        return redisService.showData(key);
    }


}