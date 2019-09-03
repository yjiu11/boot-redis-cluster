package com.ptw.redis;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
 
@Configuration
public class RedissonClientConfig {
 
    @Value("${spring.redis.cluster.nodes}")
    private String nodes;
 
    @Bean
    public RedissonClient getRedisson(){
    	String[] split = nodes.split(",");
        Config config = new Config();
        for (String node : split) {
        	config.useClusterServers().addNodeAddress("redis://" + node);
		}
        return Redisson.create(config);
    }
}