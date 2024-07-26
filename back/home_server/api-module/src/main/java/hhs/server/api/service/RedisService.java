package hhs.server.api.service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RedisService {
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisService(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public String saveData(String key, String value){
        stringRedisTemplate.opsForValue().set(key, value);
        return key+" "+value+" saved";
    }

    public String showData(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

}