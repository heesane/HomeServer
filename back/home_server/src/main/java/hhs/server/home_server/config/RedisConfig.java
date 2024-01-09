package hhs.server.home_server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import static java.time.Duration.ofMillis;

@Configuration
public class RedisConfig {
    private final String HOSTNAME;
    private final Integer PORT;

    private final Integer DATABASE;

    private final String PASSWORD;

    private final Long TIMEOUT;

    public RedisConfig(
            @Value("${redis.host}") String HOSTNAME,
            @Value("${redis.port}") Integer PORT,
            @Value("${redis.database}") Integer DATABASE,
            @Value("${redis.password}") String PASSWORD,
            @Value("${redis.timeout}") Long TIMEOUT
    ){
        this.HOSTNAME = HOSTNAME;
        this.PORT = PORT;
        this.DATABASE = DATABASE;
        this.PASSWORD = PASSWORD;
        this.TIMEOUT = TIMEOUT;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(HOSTNAME);
        config.setPort(PORT);
        config.setDatabase(DATABASE);
        config.setPassword(PASSWORD);

        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .commandTimeout(ofMillis(TIMEOUT))
                .build();

        return new LettuceConnectionFactory(config, clientConfig);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        // Key : Value 형식으로 사용할 경우 시리얼라이저
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        // Hash를 사용할 경우 시리얼라이저
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());

        // 모든 경우
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());

        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory());

        // Key : Value 형식으로 사용할 경우 시리얼라이저
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setValueSerializer(new StringRedisSerializer());
        return stringRedisTemplate;
    }
}
