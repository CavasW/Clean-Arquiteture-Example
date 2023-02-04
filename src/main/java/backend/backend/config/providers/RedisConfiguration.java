package backend.backend.config.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import backend.backend.config.settings.RedisCredentials;

@Configuration
@EnableRedisRepositories
public class RedisConfiguration {

    @Autowired
    private RedisCredentials credentials;

    @Bean
    RedisConnectionFactory jedisConnectionFactory() {
        return new LettuceConnectionFactory(redisConfiguration());
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
  
        RedisTemplate<byte[], byte[]> template = new RedisTemplate<byte[], byte[]>();
        template.setConnectionFactory(redisConnectionFactory);
        
        return template;
    
    }

    private RedisStandaloneConfiguration redisConfiguration() {

        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(credentials.getHost());
        configuration.setPort(credentials.getPort());

        return configuration;

    }

}
